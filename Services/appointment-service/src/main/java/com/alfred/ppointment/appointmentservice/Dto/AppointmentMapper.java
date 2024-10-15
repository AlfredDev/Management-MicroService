package com.alfred.ppointment.appointmentservice.Dto;

import com.alfred.ppointment.appointmentservice.model.Appointment;
import org.springframework.stereotype.Service;

import static com.alfred.ppointment.appointmentservice.model.AppointmentStatus.SCHEDULED;

@Service
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentRequest request) {
        return Appointment.builder()
                .patientId(request.patientId())
                .status(SCHEDULED)
                .appointmentDate(request.appointmentDate())
                .notes(request.notes())
                .price(request.price())
                .build();
    }

    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getNotes(),
                appointment.getPrice()
        );
    }
}
