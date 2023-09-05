package com.BooksShopBackend.REST.API.models;

import org.springframework.http.HttpStatus;

public class ApplicationError extends RuntimeException {
    private String message;

    public ApplicationError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}