package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderGetResponseDTO {
    private OrderDataDTO orderData;
    private List<OrderBooksDTO> books;
    private String orderDate;
    private OrderDeliveryAddressDTO deliveryAddress;
    private String deliveryDate;
    private String additionalInformation;
    private List<String> gifts;
    private String paymentType;
    public OrderGetResponseDTO() {
        super();
    }
//    public OrderGetResponseDTO(OrderDataDTO orderData, List<OrderBooksDTO> books,
//                               OrderDeliveryAddressDTO deliveryAddress, String orderDate,
//                               String deliveryDate, String additionalInformation,
//                               List<String> gifts, String paymentType) {
//        this.orderData = orderData;
//        this.books = books;
//        this.deliveryAddress = deliveryAddress;
//        this.orderDate = orderDate;
//        this.deliveryDate = deliveryDate;
//        this.additionalInformation = additionalInformation;
//        this.gifts = gifts;
//        this.paymentType = paymentType;
//    }

}
