package com.manage.quanlykytucxa.domain;

import java.time.Instant;

import com.manage.quanlykytucxa.util.constant.GenderEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

}
