package com.BooksShopBackend.REST.API.models;

public class SignupRequest {
    private String email;
    private String password;

    // Gettery i settery

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}