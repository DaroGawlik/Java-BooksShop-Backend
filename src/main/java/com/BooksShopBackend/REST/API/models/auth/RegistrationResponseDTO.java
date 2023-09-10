package com.BooksShopBackend.REST.API.models.auth;

public class RegistrationResponseDTO {
    private Integer userId;
    private String idToken;
    private String refreshToken;
    private final String expiresIn = "3600";

    public RegistrationResponseDTO() {
        super();
    }

    public RegistrationResponseDTO(String idToken, String refreshToken) {
        super();
        this.idToken = idToken;
        this.refreshToken = refreshToken;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }
}
