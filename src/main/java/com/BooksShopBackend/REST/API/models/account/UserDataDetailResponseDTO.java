package com.BooksShopBackend.REST.API.models.account;

public class UserDataDetailResponseDTO {

    private String username;

    public UserDataDetailResponseDTO() {
        super();
    }

    public UserDataDetailResponseDTO(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
