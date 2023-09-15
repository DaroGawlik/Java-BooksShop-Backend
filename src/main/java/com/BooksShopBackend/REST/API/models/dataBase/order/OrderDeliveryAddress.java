package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderDeliveryAddress")
public class OrderDeliveryAddress {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "street")
    private String street;

    @Column(name = "flatNumber")
    private String flatNumber;

    @Column(name = "houseNumber")
    private String houseNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

}