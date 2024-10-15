package com.alfred.payment.payment.mapper;

import com.alfred.payment.payment.dto.PaymentRequest;
import com.alfred.payment.payment.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .appointmentId(request.appointmentId())
                .build();
    }
}
