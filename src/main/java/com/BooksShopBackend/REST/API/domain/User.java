package com.BooksShopBackend.REST.API.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {

    private Long id;

    @NotEmpty(message = "E_mail name cannot be empty")
    @Email(message = "Invalid email. Please enter a valid email address")
    private String email;

    @NotEmpty(message = "Password name cannot be empty")
    private String password;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;
}
