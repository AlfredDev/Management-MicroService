package com.alfred.tracking.trackingservice.Dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ActivityLogRequest(

        @NotNull(message = "Patient ID is required")
        Integer patientId,

        @NotNull(message = "Date is required")
        @PastOrPresent(message = "Date cannot be in the future")
        LocalDate date,

        @NotNull(message = "Activity Type is required")
        @Size(max = 50, message = "Activity Type can be at most 50 characters long")
        String activityType,

        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be a positive number")
        int duration,

        @NotNull(message = "Calories burned is required")
        @PositiveOrZero(message = "Calories burned cannot be negative")
        int caloriesBurned
) {}