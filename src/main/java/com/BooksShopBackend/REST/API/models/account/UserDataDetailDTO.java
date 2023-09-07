package com.BooksShopBackend.REST.API.models.account;

public class UserDataDetailDTO {

    private String userId;
    public UserDataDetailDTO() {
        super();
    }
    public UserDataDetailDTO(String userId){
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
