package com.manage.quanlykytucxa.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.repository.RoleRepository;
import com.manage.quanlykytucxa.repository.UserRepository;

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
}
