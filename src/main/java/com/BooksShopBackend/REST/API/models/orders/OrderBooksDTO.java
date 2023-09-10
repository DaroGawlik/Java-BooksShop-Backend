package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;

@Getter
public class OrderBooksDTO {
    private String author;
    private String title;
    private int amount;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
