package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.quanlykytucxa.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.manage.quanlykytucxa.domain.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return ResponseEntity.ok(userService.create(user));
    }
}
