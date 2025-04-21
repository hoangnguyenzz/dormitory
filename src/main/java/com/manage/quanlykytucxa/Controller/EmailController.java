package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.service.EmailService;
import com.manage.quanlykytucxa.service.HoaDonService;
import com.manage.quanlykytucxa.service.RoomService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;
    private final HoaDonService hoaDonService;
    private final RoomService roomService;

    public EmailController(EmailService emailService, HoaDonService hoaDonService, RoomService roomService) {
        this.roomService = roomService;
        this.hoaDonService = hoaDonService;
        this.emailService = emailService;
    }

    @GetMapping("/{id}")

    public String sendEmailHoaDon(@PathVariable("id") Long id) {
        // this.emailService.sendSimpleEmail();
        // this.emailService.sendEmailFromTemplateSync("nguyenvanhoang2004bn@gmail.com",
        // "Testing from Spring Boot",
        // "job");
        this.hoaDonService.sendHoaDon(id);

        return "ok";
    }

    @GetMapping("/dangkiphong/{id}")

    public String sendEmailDangKiPhong(@PathVariable("id") Long id) {

        this.roomService.dangKiPhong(id);

        return "ok";
    }
}