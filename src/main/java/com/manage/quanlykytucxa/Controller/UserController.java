package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.quanlykytucxa.service.UserService;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse> createUser(@Valid @RequestBody User user) {
        RestResponse res = new RestResponse();
        res.setData(userService.create(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getUsers(

            @Filter Specification<User> spec, Pageable pageable) {

        return ResponseEntity.ok(this.userService.getAllUsers(spec, pageable));
    }
}
