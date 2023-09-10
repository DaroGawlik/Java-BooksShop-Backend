package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class OrderBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
};