package com.alfred.tracking.trackingservice.Dto;

import com.alfred.tracking.trackingservice.entity.ActivityType;

import java.time.LocalDate;

public record ActivityLogResponse(

        Integer id,
        Integer patientId,
        LocalDate date,
        ActivityType activityType,
        int duration,
        int caloriesBurned
) {
}