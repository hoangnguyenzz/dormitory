package com.manage.quanlykytucxa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.service.StudentService;
import com.turkraft.springfilter.boot.Filter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.studentService.createStudent(request));
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student request) {

        return ResponseEntity.ok(this.studentService.updateStudent(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return ResponseEntity.ok(this.studentService.getStudentById(id));
    }

    @GetMapping()
    public ResponseEntity<ResultPagination> getStudents(@Filter Specification<Student> spec, Pageable pageable) {
        return ResponseEntity.ok(this.studentService.getAllStudent(spec, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
