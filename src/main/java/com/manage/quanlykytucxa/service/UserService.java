package com.manage.quanlykytucxa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.RoleRepository;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.StudentRepository;
import com.manage.quanlykytucxa.repository.UserRepository;
import com.manage.quanlykytucxa.util.SecurityUtil;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository, StudentRepository studentRepository, RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User create(User user) {

        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại !");
        }
        if (this.studentRepository.existsByMaSv(user.getStudent().getMaSv())) {
            throw new RuntimeException("Mã sinh viên đã tồn tại !");
        }
        if (user.getRole() == null) {
            user.setRole(this.roleRepository.findByName("USER"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return userRepository.save(user);
    }

    public User handleGetUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    // Read method
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return user;
    }

    public ResultPagination getAllUsers(Specification<User> spec, Pageable pageable) {

        Page<User> pageUser = this.userRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageUser.getNumber() + 1);
        mt.setPageSize(pageUser.getSize());
        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageUser.getContent());

        return rs;
    }

    public List<User> getAllUsersByRoomId(Long roomId) {
        return this.userRepository.findByRoomId(roomId);
    }

    // Update method
    public User updateUser(User request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người này " + request.getId()));

        // check role
        if (request.getRole() != null) {
            Role r = this.roleRepository.findByName(request.getRole().getName());
            user.setRole(r != null ? r : null);
            System.out.println(r.getId() + " - -" + r.getName());
        }
        if (request.getRoom() != null) {
            user.setRoom(this.roomRepository.findById(request.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id " +
                            request.getRoom().getId())));
        }
        return userRepository.save(user);
    }

    public void changePassword(User user) {
        User currentUser = this.userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người này " + user.getId()));
        if (user.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.userRepository.save(currentUser);
    }

    // Delete method
    @Transactional
    public void deleteUser(Long id) {
        User user = this.getUserById(id);
        // this.studentRepository.deleteByUser(user);
        this.userRepository.deleteById(id);
    }

    public void deleteUserFromRoom(Long id) {
        User user = this.getUserById(id);
        user.setRoom(null);
        this.userRepository.save(user);
    }

    public User getCurrentUserWithToken() {
        String email = SecurityUtil.getCurrentUserLogin().isPresent()
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        if (email.equals("")) {
            throw new RuntimeException("Access Token không hợp lệ !");
        }
        User currentUser = handleGetUserByUsername(email);
        return currentUser;

    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

    public List<User> getAllUsersWithoutVehicles() {
        return this.userRepository.findUsersWithoutVehicles();
    }

    public User updateNameOrAvatar(User user) {
        User currentUser = this.userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người này " + user.getId()));
        if (user.getName() != null) {
            currentUser.setName(user.getName());
        } else if (user.getAvatar() != null) {
            currentUser.setAvatar(user.getAvatar());

        }
        return this.userRepository.save(currentUser);
    }
}
