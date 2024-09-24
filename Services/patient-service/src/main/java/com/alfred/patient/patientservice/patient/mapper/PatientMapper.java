package com.alfred.patient.patientservice.patient.mapper;

import com.alfred.patient.patientservice.patient.Patient;
import com.alfred.patient.patientservice.patient.dto.PatientRequest;
import com.alfred.patient.patientservice.patient.dto.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient toPatient(PatientRequest patientRequest) {
        if (patientRequest == null) {
            return null;
        }
        return Patient.builder()
                .firstName(patientRequest.firstName())
                .lastName(patientRequest.lastName())
                .age(patientRequest.age())
                .gender(patientRequest.gender())
                .email(patientRequest.email())
                .build();
    }

    public PatientResponse toPatientResponse(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getAge(),
                patient.getGender(),
                patient.getEmail(),
                patient.getCreateAt(),
                patient.getUpdateAt()
        );
    }
}
