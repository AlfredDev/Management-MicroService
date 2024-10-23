package com.alfred.ppointment.appointmentservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Appointment {
    @Id
    private String id;

    private Integer patientId;

    private LocalDateTime appointmentDate;

    private AppointmentStatus status;

    private String notes;
    private Double price;
}
