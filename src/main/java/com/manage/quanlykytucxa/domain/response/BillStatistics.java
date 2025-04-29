package com.manage.quanlykytucxa.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillStatistics {
    private int month;
    private double tongTien;
    private String trangThai;

    // Constructor, Getter, Setter

    public BillStatistics(int month, double tongTien, String trangThai) {
        this.month = month;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Getter, Setter...
}