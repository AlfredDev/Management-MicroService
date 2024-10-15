package com.alfred.ppointment.appointmentservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentProducer {
    private final KafkaTemplate<String, AppointmentConfirmation> kafkaTemplate;

    public void sendAppointmentConfirmation(AppointmentConfirmation confirmation) {
        log.info("Sending Appointment Confirmation");
        Message<AppointmentConfirmation> message = MessageBuilder
                .withPayload(confirmation)
                .setHeader(TOPIC, "appointment-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
