package com.uncledavecode.admin.classes.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Set<String> role;

    @Override
    public String toString() {
        return "SignupRequest [name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
    }

    public SignupRequest(@NotBlank String email, @NotBlank String name, @NotBlank String lastname,
            @NotBlank String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

}
