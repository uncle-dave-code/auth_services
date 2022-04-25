package com.uncledavecode.admin.classes.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private Boolean rememberMe;

    @Override
    public String toString() {
        return "LoginRequest [password=" + password + ", rememberMe=" + rememberMe + ", username=" + username + "]";
    }
}
