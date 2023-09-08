package com.BooksShopBackend.REST.API.models.account;

import lombok.Getter;

@Getter
public class UserDataUpdateUserNameDTO {

    private String userName;

    public UserDataUpdateUserNameDTO() {
        super();
    }

    public UserDataUpdateUserNameDTO(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
