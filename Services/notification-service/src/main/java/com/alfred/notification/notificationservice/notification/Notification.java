package com.alfred.notification.notificationservice.notification;

import com.alfred.notification.notificationservice.kafka.appintment.AppointmentConfirmation;
import com.alfred.notification.notificationservice.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private AppointmentConfirmation appointmentConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
