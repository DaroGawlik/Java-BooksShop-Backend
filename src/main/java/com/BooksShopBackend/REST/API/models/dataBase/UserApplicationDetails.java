package com.BooksShopBackend.REST.API.models.dataBase;


import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
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

