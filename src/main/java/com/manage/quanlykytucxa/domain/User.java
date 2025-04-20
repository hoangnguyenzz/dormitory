package com.manage.quanlykytucxa.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.quanlykytucxa.util.constant.GenderEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotBlank(message = "email cannot be empty ! ")
    private String email;
    private String password;
    private String phone;
    private String avatar;
    private GenderEnum gender;
    private Instant createAt;
    private Instant updateAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sinhvien_id")
    private Student student;

    public User(String name, String email, String password,
            String phone, String avatar, GenderEnum gender, Role role, Student student) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.role = role;
        this.student = student;
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
