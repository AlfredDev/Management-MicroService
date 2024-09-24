package com.alfred.patient.patientservice.patient;

import com.alfred.patient.patientservice.patient.dto.PatientResponse;
import com.alfred.patient.patientservice.patient.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;

    public List<PatientResponse> getAllPatients() {
        return this.repository.findAll()
                .stream()
                .map(mapper::toPatientResponse)
                .collect(Collectors.toList());
    }
}
