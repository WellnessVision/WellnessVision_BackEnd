package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhysicalEventBookingRepository extends JpaRepository<PhysicalEventBooking, Integer> {

    @Query(value = "SELECT * FROM physical_event_booking WHERE event_id = ?1 AND booking_state = ?2", nativeQuery = true)
    List<PhysicalEventBooking> getPhysicalEventBooking(int event_id, String booking_state);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event_booking SET event_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updateUserPhysicalEventBookingEventState(int booking_id, String event_state);

    @Query(value = "SELECT * FROM physical_event_booking WHERE event_id = ?1 AND participant_id LIKE ?2", nativeQuery = true)
    List<PhysicalEventBooking> viewPhysicalEventParticipationDetails(int event_id, String searchCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event_booking SET participant_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updatePhysicalEventParticipationState(int bookingId, String participantState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET event_state = ?2 WHERE event_id = ?1", nativeQuery = true)
    void closeEventBookingForHp(int eventId,String eventState);
}
