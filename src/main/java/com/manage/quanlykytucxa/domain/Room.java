package com.manage.quanlykytucxa.domain;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int capacity;
    private double price;
    private boolean isAvailable;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<User> users;
    private Instant createAt;
    private Instant updateAt;

    public Room(String name, int capacity, double price, boolean isAvailable) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.isAvailable = isAvailable;

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