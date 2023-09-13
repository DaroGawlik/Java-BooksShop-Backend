package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderGifts")
@Getter
@Setter
public class OrderGifts {

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "Pack as a gift")
    private String gift1;

    @Column(name = "Add postcard")
    private String gift2;

    @Column(name = "Provide 2% discount to the next time")
    private String gift3;

    @Column(name = "Branded pen or pencil")
    private String gift4;
}
