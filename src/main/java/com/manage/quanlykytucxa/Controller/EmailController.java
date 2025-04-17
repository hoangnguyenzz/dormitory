package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.service.EmailService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {

        this.emailService = emailService;
    }

    @GetMapping()

    public String sendSimpleEmail() {
        this.emailService.sendSimpleEmail();
        // this.emailService.sendEmailFromTemplateSync("nguyenvanhoang2004bn@gmail.com",
        // "Testing from Spring Boot",
        // "job");

        return "ok";
    }
}