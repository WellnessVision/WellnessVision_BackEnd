package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.HealthProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthProfessionalRepository extends JpaRepository<HealthProfessional, Integer> {
    @Query(value = "SELECT * FROM health_professional", nativeQuery = true)
    List<HealthProfessional> findAllHealthProfessionals();
}
