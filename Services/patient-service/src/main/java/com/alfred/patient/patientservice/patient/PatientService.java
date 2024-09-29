package com.alfred.patient.patientservice.patient;

import com.alfred.patient.patientservice.handler.Exception.PatientNotFoundException;
import com.alfred.patient.patientservice.patient.dto.PatientRequest;
import com.alfred.patient.patientservice.patient.dto.PatientResponse;
import com.alfred.patient.patientservice.patient.mapper.PatientMapper;
import org.apache.commons.lang.StringUtils;
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

    public int savePatient(PatientRequest request) {
        var patient = this.mapper.toPatient(request);
        return this.repository.save(patient).getId();
    }

    public int deletePatient(Integer patientId) {
        var patient = repository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + patientId + "not found"));
        repository.delete(patient);
        return patientId;
    }

    public PatientResponse getPatientById(Integer id) {
        var patient = this.repository.findById(id)
                .map(mapper::toPatientResponse)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found"));
        return patient;
    }

    public void updatePatient(PatientRequest patientRequest, Integer patientId) {
        var patient = this.repository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.format(
                        "Cannot update patient :: patient with id: %d was not Found", patientId))
                );
        mergePatient(patient, patientRequest);
        this.repository.save(patient);
    }

    private void mergePatient(Patient patient, PatientRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            patient.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            patient.setLastName(request.lastName());
        }
        if (request.age() != null) {
            patient.setAge(request.age());
        }
        if (StringUtils.isNotBlank(request.gender())) {
            patient.setGender(request.gender());
        }
        if (StringUtils.isNotBlank(request.email())) {
            patient.setEmail(request.email());
        }
    }

}
