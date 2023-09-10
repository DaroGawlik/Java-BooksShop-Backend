package com.BooksShopBackend.REST.API.repositories;

import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserApplicationDetails, Integer> {

    Optional<UserApplicationDetails> findByUserId(Integer userId);

}
