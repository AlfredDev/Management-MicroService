package com.alfred.patient.patientservice.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(
        @NotNull(message = "Patient First Name is required")
        String firstName,
        @NotNull(message = "Patient Last Name is required")
        String lastName,
        @NotNull(message = "Patient age is mandatory")
        @Min(value = 8, message = "Patient age must be between 8 and 50")
        @Max(value = 50, message = "Patient age must be less than 50")
        Integer age,
        @NotNull(message = "Patient gender is required")
        String gender,
        @Email(message = "Please enter a valid email format")
        @NotNull(message = "Patient Email is required")
        String email
) {
}
