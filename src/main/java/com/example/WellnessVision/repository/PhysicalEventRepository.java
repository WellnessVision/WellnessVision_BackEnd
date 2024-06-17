package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PhysicalEventRepository extends JpaRepository<Hall, String> {

    @Query(value = "SELECT * FROM hall WHERE capacity >= ?1 AND hall_type = ?2 AND state = ?3 ORDER BY capacity ASC ", nativeQuery = true)
    List<Hall> findByCapacityAndStatus(int capacity, String hall_type, String status);

    @Query(value = "SELECT COUNT(*) AS hall_date_count FROM physical_event WHERE date = ?2 AND hall_id = ?1", nativeQuery = true)
    Integer findByDate(String hall_id, LocalDate date);

    @Query(value = "SELECT COUNT(*) AS hall_time_count FROM physical_event WHERE date = ?2 AND hall_id = ?1 AND ( start_time >= ?4 OR end_time <= ?3)", nativeQuery = true)
    Integer findByTime(String hall_id, LocalDate date, int start_time, int end_time);

    @Query(value = "SELECT COUNT(*) AS hall_time_count FROM physical_event WHERE date = ?2 AND hall_id = ?1 AND ( start_time >= ?4 OR end_time <= ?3)", nativeQuery = true)
    Integer BookHall(String hall_id, LocalDate date, int start_time, int end_time);


    Optional<Hall> findById(String hallId);
}
