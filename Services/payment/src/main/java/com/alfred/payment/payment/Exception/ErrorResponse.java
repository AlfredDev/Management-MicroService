package com.alfred.payment.payment.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors

) {
}
