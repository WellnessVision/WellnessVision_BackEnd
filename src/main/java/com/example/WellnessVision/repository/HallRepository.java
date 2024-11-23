package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, String> {

    @Query(value = "SELECT * FROM hall", nativeQuery = true)
    List<Hall> getPhysicalEventHallsForEventManager();

    @Query(value = "SELECT * FROM hall WHERE hall_id = ?1", nativeQuery = true)
    Hall getOnePhysicalEventHallDetailsForEventManager(String hallId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET maintain_start_date = ?2, maintain_end_date = ?3 WHERE hall_id = ?1", nativeQuery = true)
    void updateHallMaintenanceStartDateAndEndDateForEventManager(String hallId, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET unavailable_date = ?2 WHERE hall_id = ?1", nativeQuery = true)
    void updateHallUnavailableDateForEventManager(String hallId, LocalDate unavailableDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET maintain_start_date = null, maintain_end_date = null, unavailable_date = null WHERE hall_id = ?1", nativeQuery = true)
    void clearHallSetDateOfHallForEventManager(String hallId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET charge = ?2, advance_percentage = ?3 WHERE hall_id = ?1", nativeQuery = true)
    void updateHallChargeAndAdvancePercentageOfHallForEventManager(String hallId, int charge, double advancePercentage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET state = ?2 WHERE hall_id = ?1", nativeQuery = true)
    void changeHallStateOfHallForEventManager(String hallId, String hallState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hall SET capacity = ?2 WHERE hall_id = ?1", nativeQuery = true)
    void updateHallCapacityOfHallForEventManager(String hallId, int newCapacity);

}

