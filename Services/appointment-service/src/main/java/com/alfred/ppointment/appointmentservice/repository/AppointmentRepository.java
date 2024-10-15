package com.alfred.ppointment.appointmentservice.repository;

import com.alfred.ppointment.appointmentservice.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, Integer> {
}
