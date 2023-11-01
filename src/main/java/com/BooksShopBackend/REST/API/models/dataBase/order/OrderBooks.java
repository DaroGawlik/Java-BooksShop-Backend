package com.BooksShopBackend.REST.API.models.dataBase.order;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(name = "OrderBooks")
@OnDelete(action = OnDeleteAction.CASCADE)
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



    @Column(name = "order_id", insertable = false, updatable = false, columnDefinition = "int")
    private Integer orderId; // Dodatkowa kolumna do zachowania relacji do zamówienia

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private BooksList book;
//
//    @Column(name = "amount")
//    private Integer amount;


}