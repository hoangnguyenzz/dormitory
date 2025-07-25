package com.manage.quanlykytucxa.domain;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.quanlykytucxa.util.constant.DoiTuongEnum;
import com.manage.quanlykytucxa.util.constant.RoomEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "phong")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int capacity;
    private double price;
    private DoiTuongEnum doiTuong;
    private RoomEnum trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<User> users;
    private Instant createAt;
    private Instant updateAt;

    public Room(String name, int capacity, double price, DoiTuongEnum doiTuong, RoomEnum trangThai) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.doiTuong = doiTuong;
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