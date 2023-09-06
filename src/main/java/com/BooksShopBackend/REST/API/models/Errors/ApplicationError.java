package com.BooksShopBackend.REST.API.models.Errors;

public class ApplicationError extends RuntimeException {
    public ApplicationError(String message) {
        super(message);
    }
}


