package com.alfred.ppointment.appointmentservice.kafka;

import com.alfred.ppointment.appointmentservice.model.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentConfirmation(
        String appointmentId,
        String patientName,
        String patientEmail,
        LocalDateTime appointmentDate,
        AppointmentStatus status,
        String notes,
        Double price
) {
}
