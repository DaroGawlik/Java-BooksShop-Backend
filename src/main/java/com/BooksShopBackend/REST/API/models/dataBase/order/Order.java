package com.BooksShopBackend.REST.API.models.dataBase.order;


import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApplication user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderData orderData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderDeliveryAddress orderDeliveryAddress;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAdditional orderAdditional;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderGifts orderGifts;
}


