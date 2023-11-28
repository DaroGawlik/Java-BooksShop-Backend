package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBooksDTO {
    private String author;
    private String title;
    private int amount;
}
