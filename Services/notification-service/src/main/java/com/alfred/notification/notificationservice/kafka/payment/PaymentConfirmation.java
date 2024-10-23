package com.alfred.notification.notificationservice.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation (
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String patientFirstname,
        String patientLastname,
        String patientEmail
) {
}
