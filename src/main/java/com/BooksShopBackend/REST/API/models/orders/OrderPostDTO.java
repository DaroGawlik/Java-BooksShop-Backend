package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPostDTO {
    private OrderDataDTO orderData;
    private List<OrderBooksDTO> books;
    private OrderDeliveryAddressDTO deliveryAddress;
    private String orderDate;
    private String deliveryDate;
    private String additionalInformation;
    private List<String> gifts;
    private String paymentType;
}
