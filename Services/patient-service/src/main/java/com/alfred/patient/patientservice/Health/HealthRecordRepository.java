package com.alfred.patient.patientservice.Health;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    Optional<HealthRecord> findByPatientId(Integer patientId);
}
