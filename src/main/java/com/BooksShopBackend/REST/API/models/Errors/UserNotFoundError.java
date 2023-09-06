package com.BooksShopBackend.REST.API.models.Errors;

public class UserNotFoundError extends ApplicationError {
    public UserNotFoundError(String message) {
        super(message);
    }
}