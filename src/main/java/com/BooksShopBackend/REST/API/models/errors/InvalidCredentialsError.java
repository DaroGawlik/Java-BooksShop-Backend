package com.BooksShopBackend.REST.API.models.errors;

public class InvalidCredentialsError extends ApplicationError {
    public InvalidCredentialsError(String message) {
        super(message);
    }
}