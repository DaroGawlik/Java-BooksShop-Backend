package com.BooksShopBackend.REST.API.models.orders;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRestDTO {
    private String orderDate;
    private String deliveryDate;
    private String additionalInformation;
    private List<String> gifts;
    private String paymentType;

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
    public void setGifts(List<String> gifts) {
        this.gifts = gifts;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
