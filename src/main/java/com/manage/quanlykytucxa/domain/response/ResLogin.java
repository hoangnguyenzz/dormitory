package com.manage.quanlykytucxa.domain.response;

import com.manage.quanlykytucxa.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResLogin {

    private String accessToken;
    private UserResLoginDTO user;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResLoginDTO {
        private long id;
        private String name;
        private String email;
        private Role role;

    }

}
