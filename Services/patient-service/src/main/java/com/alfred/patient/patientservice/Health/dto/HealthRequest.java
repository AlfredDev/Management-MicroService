package com.alfred.patient.patientservice.Health.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HealthRequest(
        Integer id,
        @NotNull(message = "The weight of the patient is not specified")
        @Positive(message = "The weight of the patient should be positive")
        double weight,
        @NotNull(message = "The height of the patient is not specified")
        @Positive(message = "The height of the patient should be positive")
        double height,
        @Size(max = 100)
        String chronicCondition,
        List<String> allergics
) {
}
