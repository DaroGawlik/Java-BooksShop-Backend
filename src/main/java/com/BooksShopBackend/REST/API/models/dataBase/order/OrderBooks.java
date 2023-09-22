package com.BooksShopBackend.REST.API.models.dataBase.order;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OrderBooks")
public class OrderBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BooksList book;

    @Column(name = "amount")
    private Integer amount;


}