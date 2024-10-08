package com.alfred.tracking.trackingservice.repository;

import com.alfred.tracking.trackingservice.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
}