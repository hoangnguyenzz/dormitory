package com.manage.quanlykytucxa.service;

import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Hoadon;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.repository.HoadonRepository;
import com.manage.quanlykytucxa.repository.SoDienNuocRepository;
import com.manage.quanlykytucxa.util.constant.InvoiceEnum;

@Service
public class HoaDonService {

    private final HoadonRepository hoadonRepository;
    private final SoDienNuocRepository soDienNuocRepository;
    public HoaDonService(HoadonRepository hoadonRepository,SoDienNuocRepository soDienNuocRepository) {
        this.hoadonRepository = hoadonRepository;
        this.soDienNuocRepository=soDienNuocRepository;
    }

    public Hoadon create(Long soDienNuocId) {
SoDienNuoc soDn = this.soDienNuocRepository.findById(soDienNuocId).orElse(null);
if (soDn!=null) {
        Hoadon hoadon = new Hoadon();
        hoadon.setSoDien(soDn.getSoDienCuoi() - soDn.getSoDienDau());
        hoadon.setSoNuoc(soDn.getSoNuocCuoi() - soDn.getSoNuocDau());
        hoadon.setTienDien(hoadon.getSoDien() * 3000);
        hoadon.setTienNuoc(hoadon.getSoNuoc() * 2000);
        hoadon.setTongTien(hoadon.getTienDien() + hoadon.getTienNuoc());
        hoadon.setTrangThai(InvoiceEnum.CHUADONG);
        hoadon.setRoom(soDn.getRoom());
        return hoadonRepository.save(hoadon);
    }
    return null;
}
}
