package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthProfessionalRepository extends JpaRepository<HealthProfessional, Integer> {
    @Query(value = "SELECT * FROM health_professional WHERE (first_name LIKE ?2 OR last_name LIKE ?2) AND profession LIKE ?1", nativeQuery = true)
    List<HealthProfessional> allHealthProfessionalDashboardProfileDetails(String modifiedSearchCode, String modifiedSearchCode_2);
}
