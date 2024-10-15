package com.alfred.payment.payment.notification;

import com.alfred.payment.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String patientFirstname,
        String patientLastname,
        String patientEmail
) {
}