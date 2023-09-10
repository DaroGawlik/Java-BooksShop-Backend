package com.BooksShopBackend.REST.API.models.errors;

public class ApplicationError extends RuntimeException {
    public ApplicationError(String message) {
        super(message);
    }
}


