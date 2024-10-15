package com.alfred.ppointment.appointmentservice.patient;

import java.time.LocalDateTime;

public record PatientResponse(
        Integer id,
        String firstName,
        String lastName,
        Integer age,
        String gender,
        String email,
        LocalDateTime createAt,
        LocalDateTime updateAt) {
}
