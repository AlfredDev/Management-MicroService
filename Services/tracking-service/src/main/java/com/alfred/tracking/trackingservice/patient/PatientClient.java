package com.alfred.tracking.trackingservice.patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "patient-service",
        url = "${application.config.patient-service}"
)
public interface PatientClient {
    Optional<PatientResponse> findPatientById(
            @PathVariable("patient-id") Integer patientId
    );
}
