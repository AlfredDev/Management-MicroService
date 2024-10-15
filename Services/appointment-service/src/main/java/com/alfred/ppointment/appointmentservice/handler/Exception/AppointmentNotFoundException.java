package com.alfred.ppointment.appointmentservice.handler.Exception;

public class AppointmentNotFoundException extends  RuntimeException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
