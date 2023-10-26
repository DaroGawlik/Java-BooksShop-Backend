package com.BooksShopBackend.REST.API.models.errors;

public class EmailAlreadyExistsExceptionError extends ApplicationError{
    public EmailAlreadyExistsExceptionError(String message) {
        super(message);
    }
}
