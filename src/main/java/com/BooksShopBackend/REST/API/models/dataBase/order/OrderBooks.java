package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "OrderBooks")
public class OrderBooks {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ElementCollection
    @CollectionTable(name = "OrderBooks_Books", joinColumns = @JoinColumn(name = "order_books_id"))
    @Column(name = "book_id")
    private List<String> books_id;



//    @ElementCollection
//    @CollectionTable(name = "OrderBooks_Books", joinColumns = @JoinColumn(name = "order_books_id"))
//    @Column(name = "book_id")
//    private List<String> books_id;
}