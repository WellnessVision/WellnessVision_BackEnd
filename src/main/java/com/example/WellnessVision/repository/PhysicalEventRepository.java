package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.PhysicalEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PhysicalEventRepository extends JpaRepository<Hall, String> {

    @Query(value = "SELECT * FROM hall WHERE capacity >= ?1 AND hall_type = ?2 AND ((state = ?3) OR (state = ?4 AND (maintain_start_date > ?6 OR maintain_end_date < ?6)) OR (state = ?5 AND unavailable_date > ?6)) ORDER BY capacity ASC ", nativeQuery = true)
    List<Hall> findByCapacityAndStatus(int capacity, String hall_type, String Available, String Maintain, String Unavailable, LocalDate date);

    @Query(value = "SELECT COUNT(*) AS hall_date_count FROM physical_event WHERE date = ?2 AND hall_id = ?1", nativeQuery = true)
    Integer findByDate(String hall_id, LocalDate date);

    @Query(value = "SELECT COUNT(*) AS hall_time_count FROM physical_event WHERE date = ?2 AND hall_id = ?1 AND ( start_time >= ?4 OR end_time <= ?3)", nativeQuery = true)
    Integer findByTime(String hall_id, LocalDate date, int start_time, int end_time);

    @Query(value = "SELECT COUNT(*) AS hall_time_count FROM physical_event WHERE date = ?2 AND hall_id = ?1 AND ( start_time >= ?4 OR end_time <= ?3)", nativeQuery = true)
    Integer BookHall(String hall_id, LocalDate date, int start_time, int end_time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET event_image = ?1, hall_capacity = ?3, total_hall_charge = ?4, advance_percentage = ?5, advance_payment = ?6, payment_id = ?7, event_title = ?8, final_event_type = ?9, ticket_price = ?10, language = ?11, event_description = ?12, account_number = ?13, account_owner_name = ?14, branch_name = ?15, bank_name = ?16 WHERE event_id = ?2", nativeQuery = true)
    void UploadEventImage(String event_image, int event_id,  int hall_capacity, int total_hall_charge, double advance_percentage, int advance_payment, int payment_id,  String title, String eventType, String ticketPrice, String language, String description, String accountNumber, String accountHolderName, String branch, String bank);


    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event SET account_number = ?2, account_owner_name = ?3, branch_name = ?4, bank_name = ?5 WHERE event_id = ?1", nativeQuery = true)
    void updatePhysicalEventMoneyReceiptsDetailsForHP(int event_id, String account_number,  String account_owner_name, String branch_name, String bank_name);

    @Query(value = "SELECT * FROM hall WHERE hall_type = ?1 AND ((state = ?3) OR (state = ?4 AND (maintain_start_date > ?2 OR maintain_end_date < ?2)) OR (state = ?5 AND unavailable_date > ?2)) ORDER BY capacity ASC ", nativeQuery = true)
    List<Hall> checkTheEventHallUsingDateForPhysicalEvent(String hallType, LocalDate date, String Available, String Maintain, String Unavailable);


    Optional<Hall> findById(String hallId);


}
