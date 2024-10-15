package com.alfred.ppointment.appointmentservice.handler.Exception;


public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
