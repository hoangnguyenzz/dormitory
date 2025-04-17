package com.manage.quanlykytucxa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Hoadon;
import com.manage.quanlykytucxa.domain.SoDienNuoc;
import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.HoadonRepository;
import com.manage.quanlykytucxa.repository.SoDienNuocRepository;
import com.manage.quanlykytucxa.util.constant.InvoiceEnum;

@Service
public class HoaDonService {

    private final HoadonRepository hoadonRepository;
    private final SoDienNuocRepository soDienNuocRepository;

    public HoaDonService(HoadonRepository hoadonRepository, SoDienNuocRepository soDienNuocRepository) {
        this.hoadonRepository = hoadonRepository;
        this.soDienNuocRepository = soDienNuocRepository;
    }

    public Hoadon create(Long soDienNuocId) {
        SoDienNuoc soDn = this.soDienNuocRepository.findById(soDienNuocId).orElse(null);
        if (soDn != null) {
            Hoadon hoadon = new Hoadon();
            hoadon.setSoDien(soDn.getSoDienCuoi() - soDn.getSoDienDau());
            hoadon.setSoNuoc(soDn.getSoNuocCuoi() - soDn.getSoNuocDau());
            hoadon.setTienDien(hoadon.getSoDien() * 3000);
            hoadon.setTienNuoc(hoadon.getSoNuoc() * 8000);
            hoadon.setTongTien(hoadon.getTienDien() + hoadon.getTienNuoc());
            hoadon.setTrangThai(InvoiceEnum.CHUADONG);
            hoadon.setRoom(soDn.getRoom());
            return hoadonRepository.save(hoadon);
        }
        return null;
    }

    public ResultPagination getAllHoaDon(Specification<Hoadon> spec, Pageable pageable) {

        Page<Hoadon> pageUser = this.hoadonRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageUser.getNumber() + 1);
        mt.setPageSize(pageUser.getSize());
        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageUser.getContent());

        return rs;
    }

    public void delete(Long id){
        this.hoadonRepository.deleteById(id);
    }

}
