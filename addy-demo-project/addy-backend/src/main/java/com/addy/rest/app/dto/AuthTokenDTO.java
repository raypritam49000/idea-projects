package com.addy.rest.app.dto;


public class AuthTokenDTO {
    private String token;

    public AuthTokenDTO(String token) {
        this.token = token;
    }

    public AuthTokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthTokenDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
