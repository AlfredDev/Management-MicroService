package com.alfred.tracking.trackingservice.handler.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors

) {
}
