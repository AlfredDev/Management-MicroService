package com.alfred.ppointment.appointmentservice.Dto;


import com.alfred.payment.payment.model.PaymentMethod;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record AppointmentRequest(

        @NotNull(message = "Appointments required a valid patient identifier")
        @Positive(message = "Please enter a valid patient identifier")
        Integer patientId,
        @NotNull(message = "Please enter a valid date for the appointment")
        @FutureOrPresent(message = "Date must be present or future")
        LocalDateTime appointmentDate,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @Length(max = 240)
        String notes,
        @Positive(message = "Price Must be positive")
        @NotNull(message = "Please enter a price for the appointment")
        Double price

) {
}
