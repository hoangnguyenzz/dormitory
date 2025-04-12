package com.manage.quanlykytucxa.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqSoDienNuoc {
    private long roomId;
    private int soDienCuoi;
    private int soNuocCuoi;
}
