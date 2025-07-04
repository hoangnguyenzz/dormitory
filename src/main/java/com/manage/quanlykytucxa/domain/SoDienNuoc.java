package com.manage.quanlykytucxa.domain;

import java.time.Instant;

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
public class SoDienNuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private int soDienDau;
    private int soDienCuoi;
    private int soNuocDau;
    private int soNuocCuoi;
    private Instant createAt;

    // Tạo data
    @PrePersist
    public void handleBeforeCreate() {

        this.createAt = Instant.now();
    }
}
