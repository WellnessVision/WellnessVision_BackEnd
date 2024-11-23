package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

    @Query(value = "SELECT * FROM volunteer WHERE volunteer_id = ?1", nativeQuery = true)
    Volunteer getVolunteerDetailsByVolunteerId(int volunteerId);

    @Query(value = "SELECT * FROM volunteer", nativeQuery = true)
    List<Volunteer> adminViewAllVolunteersForAdmin();

}
