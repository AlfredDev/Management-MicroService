package com.alfred.patient.patientservice.Health;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllergicRepository extends JpaRepository<Allergic, Integer> {
    Optional<Allergic> findByName(String name);
}
