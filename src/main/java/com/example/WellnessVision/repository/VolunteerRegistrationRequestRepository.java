package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.model.VolunteerRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRegistrationRequestRepository extends JpaRepository<VolunteerRegistrationRequest, Integer> {
    @Query(value = "SELECT * FROM volunteer_registration_request", nativeQuery = true)
    List<VolunteerRegistrationRequest> viewVolunteerRegistrationRequestForAdmin();

    @Query(value = "SELECT * FROM volunteer_registration_request WHERE request_id = ?1", nativeQuery = true)
    VolunteerRegistrationRequest viewOneVolunteerRegistrationRequestForAdmin(int requestId);


}
