package com.manage.quanlykytucxa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.Vipham;

public interface ViphamRepository extends JpaRepository<Vipham, Long>, JpaSpecificationExecutor<Vipham> {

}
