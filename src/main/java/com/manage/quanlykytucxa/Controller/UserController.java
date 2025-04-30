package com.manage.quanlykytucxa.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.manage.quanlykytucxa.service.UserService;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.RestResponse;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse> createUser(@Valid @RequestBody User user) {
        RestResponse res = new RestResponse();
        res.setData(userService.create(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping()
    public ResponseEntity<RestResponse> updateUser(@RequestBody User user) {
        RestResponse res = new RestResponse();
        res.setData(userService.updateUser(user));

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getUsers(

            @Filter Specification<User> spec, Pageable pageable) {

        return ResponseEntity.ok(this.userService.getAllUsers(spec, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byroom/{id}")
    public ResponseEntity<RestResponse> getUsersByRoom(@PathVariable("id") long id) {
        RestResponse res = new RestResponse();
        res.setData(this.userService.getAllUsersByRoomId(id));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteuserfromroom/{id}")
    public ResponseEntity<String> deleteUserFromRoom(@PathVariable("id") long id) {

        this.userService.deleteUserFromRoom(id);
        return ResponseEntity.ok("Xóa thành công !");
    }

    @GetMapping("/novehicle")
    public ResponseEntity<RestResponse> getUsersWithoutVehicles() {
        RestResponse res = new RestResponse();
        res.setData(this.userService.getAllUsersWithoutVehicles());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<Map<String, Object>>> getUserStatistics(
            @RequestParam("month1") int month1,
            @RequestParam("month2") int month2,
            @RequestParam("year") int year) {
        List<Object[]> data = userRepository.countUserByMonths(month1, month2, year);

        List<Map<String, Object>> result = data.stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put("month", row[0]);
            map.put("total", row[1]);
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updatenameoravatar")
    public ResponseEntity<RestResponse> updateName(@RequestBody User user) {
        RestResponse res = new RestResponse();
        res.setData(userService.updateNameOrAvatar(user));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("clearroom/{id}")
    public ResponseEntity<Void> clearRoom(@PathVariable("id") long id) {
        this.userRepository.clearRoomByRoomId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/changepassword")
    public ResponseEntity<Void> changePassword(@RequestBody User user) {
        this.userService.changePassword(user);
        return ResponseEntity.noContent().build();
    }

}