package com.alfred.ppointment.appointmentservice.payment;

import com.alfred.payment.payment.model.PaymentMethod;
import com.alfred.ppointment.appointmentservice.patient.PatientResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        double amount,
        PaymentMethod paymentMethod,
        Integer appointmentId,
        PatientResponse customer
) {
}