package com.manage.quanlykytucxa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.Vehicle;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.VehicleRepository;

@Service
public class VehicleService {


    private final VehicleRepository vehicleRepository;
    private final UserService userService;

    
    
    public VehicleService(VehicleRepository vehicleRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }
    public Vehicle create (Vehicle vehicle){
        boolean check = this.vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate());
        if(check){
            throw new RuntimeException("Biển số xe đã tồn tại !");
        }
        vehicle.setUser(this.userService.getUserById(vehicle.getUser().getId()));
        return this.vehicleRepository.save(vehicle);
    }
 public ResultPagination getAllVehicles(Specification<Vehicle> spec, Pageable pageable) {

        Page<Vehicle> pageStudent = this.vehicleRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageStudent.getNumber() + 1);
        mt.setPageSize(pageStudent.getSize());
        mt.setPages(pageStudent.getTotalPages());
        mt.setTotal(pageStudent.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageStudent.getContent());

        return rs;
    }
public void delete(Long id){
    this.vehicleRepository.deleteById(id);
}
}
