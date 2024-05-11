package com.example.marketplace.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String email;
    private String token;

    public LoginResponse(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
}
