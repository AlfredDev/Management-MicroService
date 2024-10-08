package com.alfred.tracking.trackingservice.mapper;

import com.alfred.tracking.trackingservice.Dto.ActivityLogRequest;
import com.alfred.tracking.trackingservice.Dto.ActivityLogResponse;
import com.alfred.tracking.trackingservice.entity.ActivityLog;
import com.alfred.tracking.trackingservice.entity.ActivityType;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogMapper {
    public ActivityLog toActivityLog(ActivityLogRequest request) {
        if (request == null) {
            return null;
        }
        return ActivityLog.builder()
                .activityType(ActivityType.valueOf(request.activityType()))
                .patientId(request.patientId())
                .date(request.date())
                .duration(request.duration())
                .caloriesBurned(request.caloriesBurned())
                .build();
    }

    public ActivityLogResponse toActivityLogResponse(ActivityLog activityLog) {
        if (activityLog == null) return null;

        return new ActivityLogResponse(
                activityLog.getId(),
                activityLog.getPatientId(),
                activityLog.getDate(),
                activityLog.getActivityType(),
                activityLog.getDuration(),
                activityLog.getCaloriesBurned()
        );
    }
}
