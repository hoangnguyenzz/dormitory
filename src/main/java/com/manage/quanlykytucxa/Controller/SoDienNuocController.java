package com.manage.quanlykytucxa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.quanlykytucxa.domain.request.ReqSoDienNuoc;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.service.SoDienNuocService;

@Controller
@RequestMapping("/api/v1/sodiennuoc")
public class SoDienNuocController {

    private final SoDienNuocService soDienNuocService;

    public SoDienNuocController(SoDienNuocService soDienNuocService) {
        this.soDienNuocService = soDienNuocService;
    }

    @PostMapping()
    public ResponseEntity<RestResponse> createSoDienNuoc(@RequestBody ReqSoDienNuoc request) {
        RestResponse res = new RestResponse();
        res.setData(soDienNuocService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.soDienNuocService.delete(id);
        return ResponseEntity.noContent().build();
    }
}