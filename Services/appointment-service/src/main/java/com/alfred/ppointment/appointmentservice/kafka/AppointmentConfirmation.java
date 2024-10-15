package com.alfred.ppointment.appointmentservice.kafka;

import com.alfred.ppointment.appointmentservice.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentConfirmation(
        Integer appointmentId,
        Integer patientId,
        LocalDateTime appointmentDate,
        AppointmentStatus status,
        String notes,
        Double price
) {
}
