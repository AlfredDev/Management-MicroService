package com.alfred.ppointment.appointmentservice.service;

import com.alfred.ppointment.appointmentservice.Dto.AppointmentMapper;
import com.alfred.ppointment.appointmentservice.Dto.AppointmentRequest;
import com.alfred.ppointment.appointmentservice.Dto.AppointmentResponse;
import com.alfred.ppointment.appointmentservice.handler.Exception.AppointmentNotFoundException;
import com.alfred.ppointment.appointmentservice.handler.Exception.BusinessException;
import com.alfred.ppointment.appointmentservice.kafka.AppointmentConfirmation;
import com.alfred.ppointment.appointmentservice.kafka.AppointmentProducer;
import com.alfred.ppointment.appointmentservice.model.Appointment;
import com.alfred.ppointment.appointmentservice.patient.PatientClient;
import com.alfred.ppointment.appointmentservice.payment.PaymentClient;
import com.alfred.ppointment.appointmentservice.payment.PaymentRequest;
import com.alfred.ppointment.appointmentservice.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository repository;
    private final PatientClient client;
    private final AppointmentMapper mapper;
    private final PaymentClient paymentClient;
    private final AppointmentProducer appointmentProducer;

    @Transactional
    public String createAppointment(AppointmentRequest request) {
        var patient = client.findPatientById(request.patientId())
                .orElseThrow(() -> new BusinessException("Can not create appointment, patient not found with id " + request.patientId()));
        log.info(patient.toString());
        System.out.println(patient.toString());
        var appointment = repository.save(this.mapper.toAppointment(request));

        var paymentRequest = new PaymentRequest(
                request.price(),
                request.paymentMethod(),
                appointment.getId(),
                patient
        );

        log.info(paymentRequest.toString());
        System.out.println(paymentRequest.toString());


        paymentClient.requestOrderPayment(paymentRequest);

        appointmentProducer.sendAppointmentConfirmation(
                new AppointmentConfirmation(
                        appointment.getId(),
                        patient.firstName() + " " + patient.lastName(),
                        patient.email(),
                        request.appointmentDate(),
                        appointment.getStatus(),
                        request.notes(),
                        request.price()
                )
        );

        return appointment.getId();
    }

    public List<AppointmentResponse> findAllAppointments() {
        return this.repository.findAll()
                .stream()
                .map(mapper::toAppointmentResponse)
                .collect(Collectors.toList());
    }

    public AppointmentResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(mapper::toAppointmentResponse)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        String.format("Could not find appointment with id %d", id)));

    }

}
