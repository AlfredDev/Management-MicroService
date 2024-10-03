package com.alfred.patient.patientservice.Health.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record HealthRecordResponse(
        Integer id,
        double weight,
        double height,
        String chronicCondition,
        double bmi,
        LocalDateTime updatedAt,
        Set<String> allergics
) {
}