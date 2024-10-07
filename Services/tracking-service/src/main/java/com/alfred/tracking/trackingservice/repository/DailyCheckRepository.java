package com.alfred.tracking.trackingservice.repository;

import com.alfred.tracking.trackingservice.entity.DailyCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyCheckRepository extends JpaRepository<DailyCheck, Integer> {
}