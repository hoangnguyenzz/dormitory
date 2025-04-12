package com.manage.quanlykytucxa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.SoDienNuoc;

public interface SoDienNuocRepository extends JpaRepository<SoDienNuoc, Long>, JpaSpecificationExecutor<SoDienNuoc> {
    Optional<SoDienNuoc> findFirstByRoomOrderByCreateAtDesc(Room room);

}
