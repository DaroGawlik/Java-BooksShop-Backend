package com.BooksShopBackend.REST.API.models.dataBase;


import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderBooks;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BooksList")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String author;

    private String title;

    private Integer price;
}

