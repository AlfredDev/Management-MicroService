package com.alfred.tracking.trackingservice.repository;

import com.alfred.tracking.trackingservice.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
}