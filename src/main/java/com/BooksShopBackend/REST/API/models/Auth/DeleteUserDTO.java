package com.BooksShopBackend.REST.API.models.Auth;

public class DeleteUserDTO {

    private Integer userId;
    private String idToken;

    public DeleteUserDTO(){
        super();
    }

    public DeleteUserDTO(Integer userId, String idToken){
        super();
        this.userId = userId;
        this.idToken = idToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
