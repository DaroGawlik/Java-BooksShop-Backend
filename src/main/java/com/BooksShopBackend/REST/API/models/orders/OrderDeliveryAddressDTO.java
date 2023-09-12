package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeliveryAddressDTO {
    private String street;
    private String houseNumber;
    private String flatNumber;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}

