package com.alfred.patient.patientservice.handler.Exception;

public class HealthNotFoundException extends RuntimeException {
    public HealthNotFoundException(String message) {
        super(message);
    }

}
