package com.BooksShopBackend.REST.API.models.Auth;

public class RegistrationDTO {
    private String username;
    private String email;
    private String password;

    private Boolean returnSecureToken;


    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String email, String password, Boolean returnSecureToken){
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.returnSecureToken = returnSecureToken;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }


    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public Boolean getReturnSecureToken() {
        return this.returnSecureToken;
    }

    public void setReturnSecureToken(Boolean returnSecureToken) {
        this.returnSecureToken = returnSecureToken;
    }
    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }


}
