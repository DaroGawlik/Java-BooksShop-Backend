package com.BooksShopBackend.REST.API.models.account;

public class UserDataDetailResponseDTO {

    private String userName;

    public UserDataDetailResponseDTO() {
        super();
    }

    public UserDataDetailResponseDTO(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
