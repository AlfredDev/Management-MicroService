package com.alfred.payment.payment.dto;

import com.alfred.payment.payment.model.Patient;
import com.alfred.payment.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String appointmentId,
        Patient patient
) {
}