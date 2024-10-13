package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PhysicalEventOrderRepository extends JpaRepository<PhysicalEvent, Integer> {
    @Query(value = "SELECT * FROM physical_event WHERE hp_id = ?1 AND event_state = ?2 AND event_title LIKE ?3", nativeQuery = true)
    List<PhysicalEvent> getPhysicalEventForHP(int hp_id, String eventState, String searchCode);

    @Query(value = "SELECT * FROM physical_event WHERE event_id = ?1", nativeQuery = true)
    PhysicalEvent getOnePhysicalEventDetailForHP(int event_id);

    @Query(value = "SELECT * FROM physical_event WHERE event_id = ?1", nativeQuery = true)
    PhysicalEvent getEventDate(int event_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM physical_event WHERE event_id = ?1", nativeQuery = true)
    void deletePhysicalEvent(int event_id);

    @Query(value = "SELECT * FROM physical_event WHERE hall_id = ?1 AND date = ?2 ORDER BY start_time ASC, end_time ASC", nativeQuery = true)
    List<PhysicalEvent> getHallBookingAvailableSlotsForGivenHallAndDate(String hallId, LocalDate date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET volunteer_type = ?2, volunteer_description = ?3, volunteer_need_state = ?4 WHERE event_id = ?1", nativeQuery = true)
    void addNewVolunteerNeedRequestForHP(int eventId, String volunteerType, String volunteerDescription, String volunteerNeedState);

    @Query(value = "SELECT * FROM physical_event WHERE event_id = ?1", nativeQuery = true)
    PhysicalEvent getHpIdByEventIdForHp(int event_id);

}
