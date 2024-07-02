package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventPayment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhysicalEventPaymentRepository extends JpaRepository<PhysicalEventPayment, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE physical_event_payment SET event_state = ?2 WHERE payment_id = ?1", nativeQuery = true)
    void updatePaymentState(int payment_id, String eventState);
}
