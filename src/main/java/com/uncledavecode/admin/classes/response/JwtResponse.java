package com.uncledavecode.admin.classes.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String name;
    private String lastname;
    private List<String> roles;
    

    public JwtResponse(String accessToken, Long id, String email, String name, String lastname, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.roles = roles;
      }
}
