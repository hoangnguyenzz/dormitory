package com.manage.quanlykytucxa.service;

import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.NguoiDiLam;
import com.manage.quanlykytucxa.repository.NguoiDiLamRepository;

@Service
public class NguoiDiLamService {

    private final NguoiDiLamRepository nguoiDiLamRepository;

    public NguoiDiLamService(NguoiDiLamRepository nguoiDiLamRepository) {
        this.nguoiDiLamRepository = nguoiDiLamRepository;
    }

    public NguoiDiLam update(NguoiDiLam request) {
        NguoiDiLam nguoiDiLam = this.nguoiDiLamRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Người đi làm không tồn tại với id " + request.getId()));
        nguoiDiLam.setCongViec(request.getCongViec());
        nguoiDiLam.setDiaChi(request.getDiaChi());
        return nguoiDiLamRepository.save(nguoiDiLam);
    }

}
