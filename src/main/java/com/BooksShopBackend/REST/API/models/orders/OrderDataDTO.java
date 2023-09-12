package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;

@Getter
public class OrderDataDTO {

    private String name;
    private String surname;

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

}