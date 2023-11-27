package com.BooksShopBackend.REST.API.models.errors;

public class OrderNotFoundError extends ApplicationError {
    public OrderNotFoundError(String message) {
        super(message);
    }
}