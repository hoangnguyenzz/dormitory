package com.manage.quanlykytucxa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.Student;
import com.manage.quanlykytucxa.domain.User;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    boolean existsByStudentCode(String studentCode);

    Student findByStudentCode(String studentCode);

    List<Student> findByIdIn(List<Long> id);

    Student findByUser(User user);

    void deleteByUser(User user);
}
