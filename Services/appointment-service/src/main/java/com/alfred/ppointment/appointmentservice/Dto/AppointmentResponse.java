package com.alfred.ppointment.appointmentservice.Dto;

import com.alfred.ppointment.appointmentservice.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(
        String id,

        Integer patientId,

        LocalDateTime appointmentDate,

        AppointmentStatus status,
        String notes,
         Double price

) {
}
