package com.manage.quanlykytucxa.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.Room;

import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.service.RoomService;
import com.turkraft.springfilter.boot.Filter;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping()
    public ResponseEntity<Room> createRoom(@RequestBody Room request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.roomService.create(request));
    }

    @PutMapping()
    public ResponseEntity<Room> updateRoom(@RequestBody Room request) {
        return ResponseEntity.ok(this.roomService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.roomService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getRooms(

            @Filter Specification<Room> spec, Pageable pageable) {

        return ResponseEntity.ok(this.roomService.getAllRooms(spec, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> getDeleteById(@PathVariable("id") long id) {
        this.roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
