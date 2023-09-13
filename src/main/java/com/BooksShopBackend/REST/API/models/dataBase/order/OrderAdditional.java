package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "OrderAdditional")
@Getter
@Setter
public class OrderAdditional {

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Temporal(TemporalType.TIMESTAMP) // Jeśli używasz typu java.util.Date
    @Column(name = "orderDate")
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP) // Jeśli używasz typu java.util.Date
    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "additionalInformation")
    private String additionalInformation;

    @Column(name = "paymentType")
    private String paymentType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;
}
