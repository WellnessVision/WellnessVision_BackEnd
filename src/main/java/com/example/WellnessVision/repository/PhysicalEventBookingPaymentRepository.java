package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhysicalEventBookingPaymentRepository extends JpaRepository<PhysicalEventBookingPayment, Integer> {

    @Query(value = "SELECT * FROM physical_event_booking_payment WHERE user_id = ?1 ORDER BY payment_date DESC", nativeQuery = true)
    List<PhysicalEventBookingPayment> viewNormalUserPhysicalEventBookingPaymentForNU(int user_id);

    @Query(value = "SELECT * FROM physical_event_booking_payment WHERE payment_id = ?1", nativeQuery = true)
    PhysicalEventBookingPayment viewOneNormalUserPhysicalEventBookingPaymentForNU(int payment_id);
}
