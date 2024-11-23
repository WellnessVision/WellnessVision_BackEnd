package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.AppointmentBookingPaymentForUser;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentBookingPaymentForUserRepository extends JpaRepository<AppointmentBookingPaymentForUser, Integer> {
    @Query(value = "SELECT * FROM appointment_booking_payment_for_user WHERE appointment_booking_id = ?1", nativeQuery = true)
    AppointmentBookingPaymentForUser getOneAppointmentBookingPaymentForNU(int appointmentBookingId);

}
