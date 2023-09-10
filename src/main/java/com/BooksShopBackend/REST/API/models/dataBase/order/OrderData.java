package com.BooksShopBackend.REST.API.models.dataBase.order;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderData")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "name")
    private String name; // Dodana kolumna "author"

    @Column(name = "surname")
    private String surname; // Dodana kolumna "title"

    // Gettery i settery dla nowych pól

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
