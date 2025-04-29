package com.manage.quanlykytucxa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manage.quanlykytucxa.domain.Hoadon;

public interface HoadonRepository extends JpaRepository<Hoadon, Long>, JpaSpecificationExecutor<Hoadon> {


      @Query("SELECT h FROM Hoadon h WHERE h.room.id = :roomId AND YEAR(h.createAt) = :year")
    List<Hoadon> findByRoomIdAndYear(@Param("roomId") Long roomId, @Param("year") int year);
}
