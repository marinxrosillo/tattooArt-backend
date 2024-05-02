package com.tattooart.web.config;

public class JWTAuthenticationResponse {

    private final String token;

    public JWTAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}