package com.alfred.payment.payment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
public record Patient(
        Integer id,
        @NotNull(message = "Firstname is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,
        Integer age,
        String gender,
        @NotNull(message = "Email is required")
        @Email(message = "The customer email is not correctly formatted")
        String email,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
