package com.alfred.patient.patientservice.patient;

import com.alfred.patient.patientservice.Health.HealthRecord;
import com.alfred.patient.patientservice.patient.dto.PatientRequest;
import com.alfred.patient.patientservice.patient.mapper.PatientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @Mock
    private PatientMapper mapper;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void savePatient() {
        PatientRequest request = new PatientRequest("John",
                "Doe", 30,
                "Male",
                "john.doe@example.com");
        Patient patient = Patient.builder()
                .id(1)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .age(request.age())
                .gender(request.gender())
                .email(request.email())
                .healthRecord(new HealthRecord())
                .build();
        when(mapper.toPatient(request)).thenReturn(patient);
        when(repository.save(patient)).thenReturn(patient);
        int result = patientService.savePatient(request);

        // Assert
        assertEquals(patient.getId(), result);
        verify(mapper).toPatient(request);
        verify(repository).save(patient);
    }

    @Test
    void getPatientById() {
    }
}