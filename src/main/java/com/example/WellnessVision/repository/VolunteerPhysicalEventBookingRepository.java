package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;

import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.model.VolunteerPhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VolunteerPhysicalEventBookingRepository extends JpaRepository<VolunteerPhysicalEventBooking, Integer>{

    @Query(value = "SELECT * FROM volunteer_physical_event_booking WHERE volunteer_id = ?1 AND event_id = ?2 AND booking_state = ?3", nativeQuery = true)
    VolunteerPhysicalEventBooking getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer(int VolunteerId, int eventId, String bookingState);
    @Query(value = "SELECT COUNT(*) AS volunteerFlag From volunteer_physical_event_booking WHERE volunteer_id = ?1 AND event_id = ?2 AND booking_state = ?3", nativeQuery = true)
    Integer checkAlreadyVolunteeredPhysicalEventsForVolunteer(int volunteerId, int eventId, String bookingState);
    @Query(value = "SELECT * FROM volunteer_physical_event_booking WHERE event_id = ?1 AND booking_state = ?3 AND participant_id LIKE ?2", nativeQuery = true)
    List<VolunteerPhysicalEventBooking> getAllVolunteersForPhysicalEventsForHealthProfessionals(int eventId, String searchCode, String bookingState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE volunteer_physical_event_booking SET participant_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updateTheVolunteerParticipateStateForVolunteer(int bookingId, String participantState);

    @Query(value = "SELECT * FROM volunteer_physical_event_booking WHERE booking_id = ?1", nativeQuery = true)
    VolunteerPhysicalEventBooking getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals(int bookingId);

    @Query(value = "SELECT * FROM volunteer_physical_event_booking WHERE volunteer_id = ?1 AND event_id = ?2", nativeQuery = true)
    VolunteerPhysicalEventBooking getParticipantIdByVolunteerIdForVolunteer(int volunteerId, int eventId);

}
