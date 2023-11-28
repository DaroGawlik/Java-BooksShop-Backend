package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;

import java.util.List;

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
