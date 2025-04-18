// package com.manage.quanlykytucxa.service;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.domain.Specification;
// import org.springframework.stereotype.Service;

// import com.manage.quanlykytucxa.domain.Student;
// import com.manage.quanlykytucxa.domain.User;
// import com.manage.quanlykytucxa.domain.response.ResultPagination;
// import com.manage.quanlykytucxa.repository.RoomRepository;
// import com.manage.quanlykytucxa.repository.StudentRepository;
// import com.manage.quanlykytucxa.util.SecurityUtil;

// @Service
// public class StudentService {

// private final StudentRepository studentRepository;
// private final UserService userService;

// public StudentService(StudentRepository studentRepository, UserService
// userService) {
// this.studentRepository = studentRepository;
// this.userService = userService;

// }

// public Student createStudent(Student student) {
// if (this.studentRepository.existsByStudentCode(student.getStudentCode())) {
// throw new RuntimeException("Student code already exists!");
// }
// String currentUser = SecurityUtil.getCurrentUserLogin().isPresent() == true
// ? SecurityUtil.getCurrentUserLogin().get()
// : "";
// User user = userService.handleGetUserByEmail(currentUser);
// // student.setUser(user);
// return studentRepository.save(student);
// }

// public Student updateStudent(Student request) {
// Student student = studentRepository.findById(request.getId())
// .orElseThrow(() -> new RuntimeException("Student not found with id " +
// request.getId()));

// student.setStudentCode(request.getStudentCode());
// student.setSchool(request.getSchool());
// student.setYear(request.getYear());
// // if (request.getRoom() != null) {
// // student.setRoom(this.roomRepository.findById(request.getRoom().getId())
// // .orElseThrow(() -> new RuntimeException("Room not found with id " +
// // request.getRoom().getId())));
// // }
// return studentRepository.save(student);
// }

// public Student getStudentById(Long id) {
// return studentRepository.findById(id)
// .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
// }

// public ResultPagination getAllStudent(Specification<Student> spec, Pageable
// pageable) {

// Page<Student> pageStudent = this.studentRepository.findAll(spec, pageable);

// ResultPagination rs = new ResultPagination();

// ResultPagination.Meta mt = new ResultPagination.Meta();
// mt.setPage(pageStudent.getNumber() + 1);
// mt.setPageSize(pageStudent.getSize());
// mt.setPages(pageStudent.getTotalPages());
// mt.setTotal(pageStudent.getTotalElements());
// rs.setMeta(mt);
// rs.setResult(pageStudent.getContent());

// return rs;
// }

// public void deleteStudent(Long id) {
// this.studentRepository.deleteById(id);
// }
// }
