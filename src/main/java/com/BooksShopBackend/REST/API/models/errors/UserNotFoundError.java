package com.BooksShopBackend.REST.API.models.errors;

public class UserNotFoundError extends ApplicationError {
    public UserNotFoundError(String message) {
        super(message);
    }
}