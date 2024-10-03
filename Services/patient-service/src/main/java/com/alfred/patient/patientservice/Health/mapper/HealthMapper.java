package com.alfred.patient.patientservice.Health.mapper;

import com.alfred.patient.patientservice.Health.Allergic;
import com.alfred.patient.patientservice.Health.HealthRecord;
import com.alfred.patient.patientservice.Health.dto.HealthRecordResponse;
import com.alfred.patient.patientservice.Health.dto.HealthRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HealthMapper {
    public HealthRecord toHealthRecord(HealthRequest request) {
        return HealthRecord.builder()
                .weight(request.weight())
                .height(request.height())
                .chronicCondition(request.chronicCondition())
                .build();
    }

    public HealthRecordResponse toHealthRecordResponse(HealthRecord record) {
        return new HealthRecordResponse(
                record.getId(),
                record.getWeight(),
                record.getHeight(),
                record.getChronicCondition(),
                record.getBmi(),
                record.getUpdateAt(),
                record.getAllergics().stream()
                        .map(Allergic::getName)
                        .collect(Collectors.toSet())
        );
    }
}
