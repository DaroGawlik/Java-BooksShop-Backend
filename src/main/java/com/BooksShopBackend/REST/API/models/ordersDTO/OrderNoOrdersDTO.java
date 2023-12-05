package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNoOrdersDTO {
    private String message;

    public OrderNoOrdersDTO(String message) {
        this.message = message;
    }
}
