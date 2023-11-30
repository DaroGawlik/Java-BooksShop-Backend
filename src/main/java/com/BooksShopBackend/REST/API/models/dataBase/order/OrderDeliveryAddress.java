package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
//    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;
}