package com.BooksShopBackend.REST.API.models;

public class RegistrationResponseDTO {
    private String userId;
    private String username;
    private String idToken;
    private String refreshToken;
    private final String expiresIn = "3600";

    public RegistrationResponseDTO() {
        super();
    }

    public RegistrationResponseDTO(String username, String idToken, String refreshToken) {
        super();
        this.username = username;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
