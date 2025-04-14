package com.manage.quanlykytucxa.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.request.ReqSoDienNuoc;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.RoomRepository;
import com.manage.quanlykytucxa.repository.SoDienNuocRepository;

@Service
public class SoDienNuocService {

    private final SoDienNuocRepository soDienNuocRepository;
    private final RoomService roomService;

    public SoDienNuocService(SoDienNuocRepository soDienNuocRepository, RoomService roomService) {
        this.roomService = roomService;
        this.soDienNuocRepository = soDienNuocRepository;
    }

public ResultPagination getAllSoDienNuoc(Specification<SoDienNuoc> spec, Pageable pageable) {

        Page<SoDienNuoc> pageSoDn = this.soDienNuocRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageSoDn.getNumber() + 1);
        mt.setPageSize(pageSoDn.getSize());
        mt.setPages(pageSoDn.getTotalPages());
        mt.setTotal(pageSoDn.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageSoDn.getContent());

        return rs;
    }

    public SoDienNuoc create(ReqSoDienNuoc request) {
        Room room = this.roomService.getById(request.getRoomId());
        SoDienNuoc soDn = this.soDienNuocRepository.findFirstByRoomOrderByCreateAtDesc(room)
                .orElse(null);
        SoDienNuoc soDienNuoc = new SoDienNuoc();
        soDienNuoc.setRoom(room);
        soDienNuoc.setSoDienCuoi(request.getSoDienCuoi());
        soDienNuoc.setSoNuocCuoi(request.getSoNuocCuoi());
        if (soDn != null) {
            soDienNuoc.setSoDienDau(soDn.getSoDienCuoi());
            soDienNuoc.setSoNuocDau(soDn.getSoNuocCuoi());
        } else {
            soDienNuoc.setSoDienDau(0);
            soDienNuoc.setSoNuocDau(0);
        }
        return this.soDienNuocRepository.save(soDienNuoc);
    }

    public void delete(long id) {
        this.soDienNuocRepository.deleteById(id);
    }
}
