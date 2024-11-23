package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.VolunteerPhysicalEventRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.beans.Transient;
import java.util.List;

public interface VolunteerPhysicalEventRequestRepository extends JpaRepository<VolunteerPhysicalEventRequest, Integer> {

    @Query(value = "SELECT COUNT(*) AS requestFlag From volunteer_physical_event_request WHERE volunteer_id = ?1 AND event_id = ?2", nativeQuery = true)
    Integer checkVolunteerRequestForPhysicalEventsForVolunteer(int volunteerId, int eventId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM volunteer_physical_event_request WHERE volunteer_id = ?1 AND event_id = ?2", nativeQuery = true)
    void deleteTheVolunteerRequestForPhysicalEventsForVolunteer(int volunteerId, int eventId);

    @Query(value = "SELECT * From volunteer_physical_event_request WHERE event_id = ?1", nativeQuery = true)
    List<VolunteerPhysicalEventRequest> getVolunteerRequestForPhysicalEventsForHealthProfessionals(int eventId);

    @Query(value = "SELECT * From volunteer_physical_event_request WHERE request_id = ?1", nativeQuery = true)
    VolunteerPhysicalEventRequest getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(int requestId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM volunteer_physical_event_request WHERE request_id = ?1", nativeQuery = true)
    void deleteTheVolunteerRequestForPhysicalEventsForHPByRequestId(int requestId);


}
