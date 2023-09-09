package com.BooksShopBackend.REST.API.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

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

    @Column(nullable = false)
    private String userName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserApplication userApplication;

    public UserApplicationDetails(String userName) {
        this.userName = userName;
    }
}

