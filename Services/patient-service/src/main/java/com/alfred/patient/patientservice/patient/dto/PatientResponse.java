package com.alfred.patient.patientservice.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PatientResponse(
        Integer id,
        String firstName,
        String lastName,
        Integer age,
        String gender,
        String email,
        LocalDateTime createAt,
        LocalDateTime updateAt) {
}
