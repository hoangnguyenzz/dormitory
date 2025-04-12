package com.manage.quanlykytucxa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.Hoadon;

public interface HoadonRepository extends JpaRepository<Hoadon, Long>, JpaSpecificationExecutor<Hoadon> {

}
