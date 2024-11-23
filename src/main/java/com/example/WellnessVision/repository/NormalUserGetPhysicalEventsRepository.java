package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.PhysicalEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NormalUserGetPhysicalEventsRepository extends JpaRepository<PhysicalEvent, Integer> {
    @Query(value = "SELECT * FROM physical_event WHERE event_state = ?1 AND event_title LIKE ?2", nativeQuery = true)
    List<PhysicalEvent> getUpcomingPhysicalEventsForUsers(String eventState, String searchCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET ticket_booking_count = ticket_booking_count + 1 WHERE event_id = ?1", nativeQuery = true)
    void temporaryBookingPhysicalEventTicket(int event_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET ticket_booking_count = ticket_booking_count - 1 WHERE event_id = ?1", nativeQuery = true)
    void userPhysicalEventTemporaryBookingCancel(int event_id);

    @Query(value = "SELECT COUNT(*) AS checkBookingCount FROM physical_event_booking WHERE event_id = ?1 AND user_id = ?2 AND booking_state = ?3", nativeQuery = true)
    Integer checkBookingStateOfOnePhysicalEventDetailForUser(int event_id, int user_id, String bookingState);

    @Query("SELECT pe FROM PhysicalEvent pe JOIN PhysicalEventBooking peb ON pe.event_id = peb.eventId WHERE peb.userId = ?1 AND peb.bookingState = ?2 AND peb.eventState = ?3")
    List<PhysicalEvent> getBookedUpcomingPhysicalEventsForUsers(int userId, String bookingState, String eventState);

    @Query("SELECT pe FROM PhysicalEvent pe JOIN VolunteerPhysicalEventBooking veb ON pe.event_id = veb.eventId WHERE veb.volunteerId = ?1 AND veb.bookingState = ?2 AND veb.eventState = ?3")
    List<PhysicalEvent> getVolunteerUpcomingPhysicalEventsForVolunteers(int volunteerId, String bookingState, String eventState);

    @Query(value = "SELECT * FROM physical_event WHERE volunteer_need_state = ?4 AND event_state = ?1 AND event_title LIKE ?2 AND volunteer_type LIKE ?3", nativeQuery = true)
    List<PhysicalEvent> getVolunteerNeedUpcomingPhysicalEventsForVolunteer(String eventState, String searchTitleCodeModify, String searchVolunteerCodeModify, String volunteerNeedState);
}
