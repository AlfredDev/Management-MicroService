package com.alfred.patient.patientservice.patient;

import com.alfred.patient.patientservice.patient.dto.PatientRequest;
import com.alfred.patient.patientservice.patient.dto.PatientResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<Integer> createPatient(
            @RequestBody @Valid PatientRequest patientRequest
    ) {
        return ResponseEntity.status(CREATED).body(patientService.savePatient(patientRequest));
    }

    @PutMapping("/update/{patient-id}")
    public ResponseEntity<Void> updatePatientById(
            @RequestBody @Valid PatientRequest patientRequest,
            @PathVariable("patient-id") Integer patientId
    ) {
        patientService.updatePatient(patientRequest, patientId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{patient-Id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @PathVariable("patient-id") Integer patientId
    ) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @DeleteMapping("/delete/{patient-id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable("patient-id") Integer patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.accepted().build();
    }


}
