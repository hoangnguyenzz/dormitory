package com.manage.quanlykytucxa.domain;

import java.time.Instant;

import com.manage.quanlykytucxa.util.constant.XuLyViPhamEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vipham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nguoiViPham;
    private String phong;
    private String noiDung;
    private XuLyViPhamEnum trangThai;
    private Instant createAt;
    private Instant updateAt;

    public Vipham(String nguoiViPham, String phong, String noiDung, XuLyViPhamEnum trangThai) {
        this.nguoiViPham = nguoiViPham;
        this.phong = phong;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
    }

    @PrePersist
    public void handleBeforeCreate() {

        this.createAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updateAt = Instant.now();

    }
}
