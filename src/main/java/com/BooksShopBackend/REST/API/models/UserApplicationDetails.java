package com.BooksShopBackend.REST.API.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationDetails {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserApplication userApplication;

    public UserApplicationDetails(String username) {
        this.username = username;
    }
}

