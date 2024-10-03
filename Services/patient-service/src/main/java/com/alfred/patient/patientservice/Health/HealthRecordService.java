package com.alfred.patient.patientservice.Health;

import com.alfred.patient.patientservice.Health.dto.HealthRecordResponse;
import com.alfred.patient.patientservice.Health.dto.HealthRequest;
import com.alfred.patient.patientservice.Health.mapper.HealthMapper;
import com.alfred.patient.patientservice.handler.Exception.HealthNotFoundException;
import com.alfred.patient.patientservice.handler.Exception.PatientNotFoundException;
import com.alfred.patient.patientservice.patient.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;
    private final AllergicRepository allergicRepository;
    private final PatientRepository patientRepository;
    private final HealthMapper mapper;

    @Transactional
    public Integer createHealthRecord(Integer idPatient, HealthRequest request) {
        var patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new PatientNotFoundException(
                        String.format("Patient with id :: %d doesnt exist", idPatient)
                ));
        var healthRecord = mapper.toHealthRecord(request);

        assignAllergies(healthRecord, request);
        healthRecord.setPatient(patient);
        return healthRecordRepository.save(healthRecord).getId();
    }

    @Transactional
    public void updateHealthRecord(Integer idPatient, HealthRequest request) {
        var healthRecord = healthRecordRepository.findByPatientId(idPatient)
                .orElseThrow(() -> new HealthNotFoundException(
                        String.format("Could not find health record for patient %d", idPatient)
                ));

        healthRecord.setWeight(request.weight());
        healthRecord.setHeight(request.height());
        healthRecord.setChronicCondition(request.chronicCondition());

        assignAllergies(healthRecord, request);

        healthRecordRepository.save(healthRecord);
    }


    public HealthRecordResponse getHealthRecordBYPatient(Integer patientId) {
        var healthRecord = healthRecordRepository.findByPatientId(patientId)
                .map(mapper::toHealthRecordResponse)
                .orElseThrow(() -> new HealthNotFoundException(
                        String.format("Could not find health record for patient %d", patientId)));

        return healthRecord;
    }

    public HealthRecordResponse getHealthRecordById(Integer healthId) {
        var healthRecord = healthRecordRepository.findById(healthId)
                .map(mapper::toHealthRecordResponse)
                .orElseThrow(() -> new HealthNotFoundException(
                        String.format("Could not find health record with id :: %d", healthId)
                ));

        return healthRecord;
    }

    private void assignAllergies(HealthRecord healthRecord, HealthRequest request) {
        var allergicsSet = new HashSet<Allergic>();
        for (String allergicName : request.allergics()) {
            Allergic allergic = allergicRepository.findByName(allergicName)
                    .orElseGet(() -> allergicRepository.save(
                            Allergic.builder()
                                    .name(allergicName)
                                    .build()
                    ));
            allergicsSet.add(allergic);
        }
        healthRecord.setAllergics(allergicsSet);
    }
}
