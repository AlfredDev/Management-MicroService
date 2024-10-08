package com.alfred.patient.patientservice.Health;

import com.alfred.patient.patientservice.Health.dto.HealthRecordResponse;
import com.alfred.patient.patientservice.Health.dto.HealthRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthRecordController {
    private final HealthRecordService service;


    @PutMapping("{patient-id}")
    public ResponseEntity<Void> updateHealthRecord(@PathVariable("patient-id") Integer patientId,
                                                   @Valid @RequestBody HealthRequest request) {

        service.updateHealthRecord(patientId, request);
        return ResponseEntity.accepted().build();

    }

    @GetMapping("{id}")
    public ResponseEntity<HealthRecordResponse> getHealthRecordById(
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(service.getHealthRecordById(id));
    }

    @GetMapping("record/{patient-id}")
    public ResponseEntity<HealthRecordResponse> getHealthRecordByPatientId(
            @PathVariable("patient-id") Integer patientId) {
        return ResponseEntity.ok().body(service.getHealthRecordBYPatientId(patientId));
    }


}
