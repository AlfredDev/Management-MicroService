package com.alfred.notification.notificationservice.kafka;

import com.alfred.notification.notificationservice.email.EmailService;
import com.alfred.notification.notificationservice.kafka.payment.PaymentConfirmation;
import com.alfred.notification.notificationservice.notification.Notification;
import com.alfred.notification.notificationservice.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.alfred.notification.notificationservice.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        var patientName = paymentConfirmation.patientFirstname() + " " + paymentConfirmation.patientLastname();

        if (paymentConfirmation.patientEmail() == null) {
            // Log and return without attempting to send email
            log.error("PaymentConfirmation received with null customerEmail: {}", paymentConfirmation);
            return;
        }
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.patientEmail(),
                patientName,
                paymentConfirmation.amount());
    }
}
