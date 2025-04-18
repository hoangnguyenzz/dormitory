package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.request.ReqLogin;
import com.manage.quanlykytucxa.domain.response.ResLogin;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.repository.StudentRepository;
import com.manage.quanlykytucxa.service.UserService;
import com.manage.quanlykytucxa.util.SecurityUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
        private final UserService userService;
        private final SecurityUtil securityUtil;
        private final StudentRepository studentRepository;
        private AuthenticationManagerBuilder authenticationManagerBuilder;

        public AuthController(UserService userService, SecurityUtil securityUtil,
                        AuthenticationManagerBuilder authenticationManagerBuilder,
                        StudentRepository studentRepository) {
                this.studentRepository = studentRepository;
                this.userService = userService;
                this.securityUtil = securityUtil;
                this.authenticationManagerBuilder = authenticationManagerBuilder;
        }

        @PostMapping("/login")
        public ResponseEntity<RestResponse> login(@RequestBody ReqLogin request) {
                // Nạp input gồm username/password vào Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword());

                // xác thực người dùng => cần viết hàm loadUserByUsername
                Authentication authentication = authenticationManagerBuilder.getObject()
                                .authenticate(authenticationToken);
                // Xét thông tin người dùng đăng nhập vào context(có thể sử dụng sau này :)
                SecurityContextHolder.getContext().setAuthentication(authentication);

                RestResponse rest = new RestResponse();
                ResLogin res = new ResLogin();

                User currentUser = this.userService.handleGetUserByEmail(request.getEmail());
                if (currentUser != null) {
                        ResLogin.UserResLoginDTO dto = new ResLogin.UserResLoginDTO(currentUser.getId(),
                                        currentUser.getName(), currentUser.getEmail(), currentUser.getRole());

                        res.setUser(dto);
                }
                // // access token
                // String access_token =
                // this.securityUtil.createAccessToken(authentication.getName(), res);
                String accessToken = this.securityUtil.createAccessToken(request.getEmail(),
                                "hoang");

                res.setAccessToken(accessToken);
                rest.setData(res);
                return ResponseEntity.ok(rest);
        }

        @GetMapping("/account")
        public ResponseEntity<User> getAccount() {
                User currentUser = this.userService.getCurrentUserWithToken();

                return ResponseEntity.ok().body(currentUser);
        }

}
