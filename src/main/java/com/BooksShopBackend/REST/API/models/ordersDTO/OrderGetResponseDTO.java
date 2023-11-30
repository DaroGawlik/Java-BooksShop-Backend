package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderGetResponseDTO {
    private int OrderId;
    private OrderDataDTO orderData;
    private List<OrderBooksDTO> books;
    private OrderDeliveryAddressDTO deliveryAddress;
    private String deliveryDate;
    private String additionalInformation;
    private List<String> gifts;
    private String paymentType;
    public OrderGetResponseDTO() {
        super();
    }

}
