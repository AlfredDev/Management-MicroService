package com.alfred.payment.payment.service;

import com.alfred.payment.payment.dto.PaymentRequest;
import com.alfred.payment.payment.mapper.PaymentMapper;
import com.alfred.payment.payment.notification.NotificationProducer;
import com.alfred.payment.payment.notification.PaymentNotificationRequest;
import com.alfred.payment.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = this.paymentRepository.save(this.mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.amount(),
                        request.paymentMethod(),
                        request.patient().firstName(),
                        request.patient().lastName(),
                        request.patient().email()
                )
        );
        return payment.getId();
    }

}
