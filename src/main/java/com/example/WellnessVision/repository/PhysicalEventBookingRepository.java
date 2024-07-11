package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhysicalEventBookingRepository extends JpaRepository<PhysicalEventBooking, Integer> {

    @Query(value = "SELECT * FROM physical_event_booking WHERE event_id = ?1", nativeQuery = true)
    List<PhysicalEventBooking> getPhysicalEventBooking(int event_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event_booking SET event_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updateUserPhysicalEventBookingEventState(int booking_id, String event_state);


}
