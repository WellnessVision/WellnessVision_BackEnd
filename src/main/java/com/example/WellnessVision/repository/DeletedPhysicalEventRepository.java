package com.example.WellnessVision.repository;

import com.example.WellnessVision.dto.UserDetailsPhysicalEventBooking;
import com.example.WellnessVision.model.DeletedPhysicalEvent;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeletedPhysicalEventRepository extends JpaRepository<DeletedPhysicalEvent, Integer> {
    @Query(value = "SELECT * FROM deleted_physical_event WHERE hp_id = ?1 AND event_title LIKE ?2", nativeQuery = true)
    List<DeletedPhysicalEvent> viewAllDeleteHealthProfessionalPhysicalEventForHP(int hp_id, String searchCodeModify);
    @Query(value = "SELECT * FROM deleted_physical_event WHERE event_id = ?1", nativeQuery = true)
    DeletedPhysicalEvent viewOneDeleteHealthProfessionalPhysicalEventForHP(int event_id);

}
