package com.manage.quanlykytucxa.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.NguoiDiLam;
import com.manage.quanlykytucxa.service.NguoiDiLamService;

@RestController
@RequestMapping("/api/v1/nguoidilam")
public class NguoiDiLamController {

    private final NguoiDiLamService nguoiDiLamService;

    public NguoiDiLamController(NguoiDiLamService nguoiDiLamService) {
        this.nguoiDiLamService = nguoiDiLamService;
    }

    @PutMapping
    public ResponseEntity<NguoiDiLam> updateNguoiDiLam(@RequestBody NguoiDiLam request) {
        return ResponseEntity.ok(this.nguoiDiLamService.update(request));
    }
}
