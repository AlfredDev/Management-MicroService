package com.alfred.tracking.trackingservice.service;

import com.alfred.tracking.trackingservice.Dto.ActivityLogRequest;
import com.alfred.tracking.trackingservice.Dto.ActivityLogResponse;
import com.alfred.tracking.trackingservice.entity.ActivityType;
import com.alfred.tracking.trackingservice.handler.Exception.BusinessException;
import com.alfred.tracking.trackingservice.mapper.ActivityLogMapper;
import com.alfred.tracking.trackingservice.patient.PatientClient;
import com.alfred.tracking.trackingservice.repository.ActivityLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogService {
    private final ActivityLogRepository activityLogRepository;
    private final PatientClient patientClient;
    private final ActivityLogMapper mapper;

    @Transactional
    public Integer createActivityLog(ActivityLogRequest request) {
        var patient = patientClient.findPatientById(request.patientId())
                .orElseThrow(() -> new BusinessException("Patient not found, cannot create activity log"));
        if (patient.age() > 60 && ActivityType.HIGH_INTENSITY.equals(request.activityType())) {
            throw new IllegalArgumentException("High-intensity activities are not allowed for patients over 60.");
        }

        return activityLogRepository.save(
                this.mapper.toActivityLog(request)
        ).getId();
    }


    public ActivityLogResponse getActivityLogById(Integer id) {
        return activityLogRepository.findById(id)
                .map(mapper::toActivityLogResponse)
                .orElseThrow(() -> new BusinessException("Activity not found with the id " + id));
    }

    public ActivityLogResponse getActivityLogByPatientId(Integer patientId) {
        return activityLogRepository.findById(patientId)
                .map(mapper::toActivityLogResponse)
                .orElseThrow(() -> new BusinessException("Activity not found with the id " + patientId));
    }

}
