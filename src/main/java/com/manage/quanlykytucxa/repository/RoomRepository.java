package com.manage.quanlykytucxa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.util.constant.RoomEnum;

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    boolean existsByName(String name);

   int countByTrangThai(RoomEnum trangThai);


}
