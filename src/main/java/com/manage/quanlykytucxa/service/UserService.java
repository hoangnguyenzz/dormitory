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
import com.manage.quanlykytucxa.repository.UserRepository;
import com.manage.quanlykytucxa.util.SecurityUtil;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User create(User user) {

        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại !");
        }
        // check role
        if (user.getRole() != null) {
            Optional<Role> r = this.roleRepository.findById(user.getRole().getId());
            user.setRole(r.get() != null ? r.get() : null);
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

    // Update method
    public User updateUser(User request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + request.getId()));

        // check role
        if (user.getRole() != null) {
            Optional<Role> r = this.roleRepository.findById(user.getRole().getId());
            user.setRole(r.get() != null ? r.get() : null);
        }
        user.setName(request.getName());

        return userRepository.save(user);
    }

    // Delete method
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
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
}
