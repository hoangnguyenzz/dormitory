package com.manage.quanlykytucxa.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.quanlykytucxa.domain.Hoadon;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.service.HoaDonService;
import com.turkraft.springfilter.boot.Filter;

@Controller
@RequestMapping("/api/v1/hoadon")
public class HoaHonController {

    private final HoaDonService hoaDonService;

    public HoaHonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<RestResponse> createHoaDon(@PathVariable("id") Long id) {
        RestResponse res = new RestResponse();
        res.setData(hoaDonService.create(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getHoaDon(

            @Filter Specification<Hoadon> spec, Pageable pageable) {

        return ResponseEntity.ok(this.hoaDonService.getAllHoaDon(spec, pageable));
    }
}
