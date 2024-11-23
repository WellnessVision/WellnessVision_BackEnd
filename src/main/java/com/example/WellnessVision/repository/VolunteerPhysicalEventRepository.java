package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;

import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.model.VolunteerPhysicalEventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VolunteerPhysicalEventRepository extends JpaRepository<Volunteer, Integer>{

    @Query(value = "SELECT * FROM volunteer WHERE volunteer_id = ?1", nativeQuery = true)
    Volunteer getVolunteerDetails(int volunteerId);

}
