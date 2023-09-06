package com.BooksShopBackend.REST.API.models.Errors;

public class InvalidCredentialsError extends ApplicationError {
    public InvalidCredentialsError(String message) {
        super(message);
    }
}