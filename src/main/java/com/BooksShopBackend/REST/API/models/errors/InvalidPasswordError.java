package com.BooksShopBackend.REST.API.models.errors;

public class InvalidPasswordError extends ApplicationError {
    public InvalidPasswordError(String message) {
        super(message);
    }

}
