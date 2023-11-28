package com.BooksShopBackend.REST.API.models.ordersDTO;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderGiftsDTO {
    private String gift1;
    private String gift2;

    public List<String> getGifts() {
        List<String> gifts = new ArrayList<>();
        if ("TRUE".equals(gift1)) {
            gifts.add(gift1);
        }
        if ("TRUE".equals(gift2)) {
            gifts.add(gift2);
        }
        return gifts;
    }
}
