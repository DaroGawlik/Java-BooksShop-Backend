package com.BooksShopBackend.REST.API.models;

import org.springframework.http.HttpStatus;

public class ApplicationError {
    private String message;

    public ApplicationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}






