package com.manage.quanlykytucxa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.NguoiDiLam;

public interface NguoiDiLamRepository extends JpaRepository<NguoiDiLam, Long>, JpaSpecificationExecutor<NguoiDiLam> {

}
