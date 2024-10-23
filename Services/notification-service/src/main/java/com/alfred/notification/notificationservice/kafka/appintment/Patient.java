package com.alfred.notification.notificationservice.kafka.appintment;

public record Patient(
        Integer id,
        String firstname,
        String lastname,
        String email
) {
}
