package com.manage.quanlykytucxa.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.Vehicle;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.service.VehicleService;
import com.turkraft.springfilter.boot.Filter;

@Controller
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping()
    public ResponseEntity<RestResponse> create(@RequestBody Vehicle vehicle) {
        RestResponse res = new RestResponse();
        res.setData(this.vehicleService.create(vehicle));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
     @GetMapping()
    public ResponseEntity<ResultPagination> getUsers(

            @Filter Specification<Vehicle> spec, Pageable pageable) {

        return ResponseEntity.ok(this.vehicleService.getAllVehicles(spec, pageable));
    }
}
