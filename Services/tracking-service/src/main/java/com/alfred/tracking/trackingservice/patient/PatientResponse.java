package com.alfred.tracking.trackingservice.patient;

public record PatientResponse(
        Integer id,
        String firstName,
        String lastName,
        Integer age,
        String gender
) {
}
