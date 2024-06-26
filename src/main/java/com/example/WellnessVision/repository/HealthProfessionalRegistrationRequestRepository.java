package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.model.PhysicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthProfessionalRegistrationRequestRepository extends JpaRepository<HealthProfessionalRegistrationRequest, Integer> {

    @Query(value = "SELECT * FROM health_professional_registration_request", nativeQuery = true)
    List<HealthProfessionalRegistrationRequest> viewHealthProfessionalRegistrationRequest();

}
