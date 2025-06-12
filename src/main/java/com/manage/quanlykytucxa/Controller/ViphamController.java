package com.manage.quanlykytucxa.Controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.Vipham;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.service.ViphamService;
import com.turkraft.springfilter.boot.Filter;

@RestController
@RequestMapping("/api/v1/vipham")
public class ViphamController {

    private final ViphamService viphamService;

    public ViphamController(ViphamService viphamService) {
        this.viphamService = viphamService;
    }

    @PostMapping
    public ResponseEntity<RestResponse> create(@RequestBody Vipham vipham) {
        RestResponse res = new RestResponse();
        res.setData(this.viphamService.create(vipham));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping
    public ResponseEntity<RestResponse> update(@RequestBody Vipham vipham) {
        RestResponse res = new RestResponse();
        res.setData(this.viphamService.update(vipham));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.viphamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getVipham(

            @Filter Specification<Vipham> spec, Pageable pageable) {

        return ResponseEntity.ok(this.viphamService.getAlls(spec, pageable));
    }
}
