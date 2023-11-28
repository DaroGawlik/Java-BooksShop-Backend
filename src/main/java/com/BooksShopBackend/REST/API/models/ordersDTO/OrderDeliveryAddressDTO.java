package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class OrderDeliveryAddressDTO {
    private String street;
    private String houseNumber;
    private String flatNumber;
}

