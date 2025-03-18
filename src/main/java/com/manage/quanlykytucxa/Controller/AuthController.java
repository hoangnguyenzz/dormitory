package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.request.ReqLogin;
import com.manage.quanlykytucxa.domain.response.ResLogin;
import com.manage.quanlykytucxa.service.UserService;
import com.manage.quanlykytucxa.util.SecurityUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final SecurityUtil securityUtil;

    public AuthController(UserService userService, SecurityUtil securityUtil) {
        this.userService = userService;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResLogin> login(@RequestBody ReqLogin request) {

        String accessToken = this.securityUtil.createAccessToken(request.getEmail(),
                "hoang");
        ResLogin res = new ResLogin();
        res.setAccessToken(accessToken);
        return ResponseEntity.ok(res);
    }

}
