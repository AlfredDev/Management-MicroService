package com.alfred.tracking.trackingservice.repository;

import com.alfred.tracking.trackingservice.entity.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepository extends JpaRepository<DietPlan, Integer> {
}