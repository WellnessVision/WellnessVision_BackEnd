package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.PhysicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NormalUserGetPhysicalEventsRepository extends JpaRepository<PhysicalEvent, Integer> {
    @Query(value = "SELECT * FROM physical_event", nativeQuery = true)
    List<PhysicalEvent> getPhysicalEventsForUser();


    @Query("SELECT e FROM PhysicalEvent e JOIN e.physicalEventBookingList b WHERE b.userId = ?1")
    List<PhysicalEvent> getBookedPhysicalEventsForUserByUserId(int userId);

}
