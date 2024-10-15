package com.alfred.ppointment.appointmentservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}