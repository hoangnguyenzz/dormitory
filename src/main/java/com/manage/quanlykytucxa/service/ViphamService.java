package com.manage.quanlykytucxa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.User;
import com.manage.quanlykytucxa.domain.Vipham;
import com.manage.quanlykytucxa.domain.response.ResultPagination;
import com.manage.quanlykytucxa.repository.ViphamRepository;
import com.manage.quanlykytucxa.util.constant.XuLyViPhamEnum;

@Service
public class ViphamService {

    private final ViphamRepository viphamRepository;

    public ViphamService(ViphamRepository viphamRepository) {
        this.viphamRepository = viphamRepository;
    }

    public Vipham create(Vipham vipham) {
        vipham.setTrangThai(XuLyViPhamEnum.CHUAXULY);
        return viphamRepository.save(vipham);
    }

    public Vipham update(Vipham vipham) {
        Vipham existingVipham = viphamRepository.findById(vipham.getId())
                .orElseThrow(() -> new RuntimeException("Vi phạm không tồn tại"));
        existingVipham.setTrangThai(vipham.getTrangThai());
        return viphamRepository.save(existingVipham);
    }

    public void delete(Long id) {
        this.viphamRepository.deleteById(id);
    }

    public ResultPagination getAlls(Specification<Vipham> spec, Pageable pageable) {

        Page<Vipham> pageVipham = this.viphamRepository.findAll(spec, pageable);

        ResultPagination rs = new ResultPagination();

        ResultPagination.Meta mt = new ResultPagination.Meta();
        mt.setPage(pageVipham.getNumber() + 1);
        mt.setPageSize(pageVipham.getSize());
        mt.setPages(pageVipham.getTotalPages());
        mt.setTotal(pageVipham.getTotalElements());
        rs.setMeta(mt);
        rs.setResult(pageVipham.getContent());

        return rs;
    }
}
