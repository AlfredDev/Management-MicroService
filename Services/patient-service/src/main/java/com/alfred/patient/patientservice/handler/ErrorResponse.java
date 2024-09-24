package com.alfred.patient.patientservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> error
) {
}
