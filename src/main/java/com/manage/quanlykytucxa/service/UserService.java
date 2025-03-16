package com.manage.quanlykytucxa.service;

import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String create(User user) {

        userRepository.save(user);

        return "ok";
    }
}
