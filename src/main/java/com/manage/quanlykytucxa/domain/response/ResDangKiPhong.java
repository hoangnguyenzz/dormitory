package com.manage.quanlykytucxa.domain.response;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResDangKiPhong {
    private  User user;
    private  Room room;
}
