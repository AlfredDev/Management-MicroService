package com.alfred.ppointment.appointmentservice.controller;

import com.alfred.ppointment.appointmentservice.Dto.AppointmentRequest;
import com.alfred.ppointment.appointmentservice.Dto.AppointmentResponse;
import com.alfred.ppointment.appointmentservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<Integer> createAppointment(@RequestBody AppointmentRequest request) {
        Integer appointmentId = service.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentId);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointments = service.findAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Integer id) {
        AppointmentResponse appointment = service.findById(id);
        return ResponseEntity.ok(appointment);
    }
}
