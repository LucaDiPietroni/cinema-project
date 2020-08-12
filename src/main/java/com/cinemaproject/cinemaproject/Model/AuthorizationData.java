package com.cinemaproject.cinemaproject.Model;

public class AuthorizationData {
    private String email;
    private String token;
    private boolean status = true;

    public AuthorizationData(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
