package com.manage.quanlykytucxa.service;

import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Hoadon;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.repository.HoadonRepository;

@Service
public class HoaDonService {

    private final HoadonRepository hoadonRepository;

    public HoaDonService(HoadonRepository hoadonRepository) {
        this.hoadonRepository = hoadonRepository;
    }

    public Hoadon create(SoDienNuoc soDienNuoc) {
        Hoadon hoadon = new Hoadon();
        hoadon.setSoDien(soDienNuoc.getSoDienCuoi() - soDienNuoc.getSoDienDau());
        hoadon.setSoNuoc(soDienNuoc.getSoNuocCuoi() - soDienNuoc.getSoNuocDau());
        hoadon.setTienDien(hoadon.getSoDien() * 3000);
        hoadon.setTienNuoc(hoadon.getSoNuoc() * 2000);
        hoadon.setTongTien(hoadon.getTienDien() + hoadon.getTienNuoc());
        return hoadonRepository.save(hoadon);
    }
}
