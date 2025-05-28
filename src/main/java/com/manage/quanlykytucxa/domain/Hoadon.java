package com.manage.quanlykytucxa.domain;

import java.time.Instant;

import com.manage.quanlykytucxa.util.constant.InvoiceEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private int soDien;
    private int soNuoc;
    private double tienDien;
    private double tienNuoc;
    private double tienPhong;
    private double tienRac;
    private double tongTien;
    private InvoiceEnum trangThai;
    private Instant createAt;

    // tạo data
    // @PrePersist
    // public void handleBeforeCreate() {

    //     this.createAt = Instant.now();
    // }
}
