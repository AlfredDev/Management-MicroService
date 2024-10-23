package com.alfred.notification.notificationservice.kafka.appintment;

import com.alfred.notification.notificationservice.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
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
