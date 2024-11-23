package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.AppointmentSchedule;
import com.example.WellnessVision.model.AppointmentScheduleRestPayment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentScheduleRestPaymentRepository  extends JpaRepository<AppointmentScheduleRestPayment, Integer> {

    @Query(value = "SELECT * FROM appointment_schedule_rest_payment WHERE appointment_schedule_id = ?1", nativeQuery = true)
    AppointmentScheduleRestPayment getAppointmentScheduleRestPaymentForOneAppointmentSchedule(int appointmentScheduleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule_rest_payment SET new_payment_start_date = ?2, remaining_advance_payment = ?3 WHERE appointment_schedule_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleRestPaymentNewPaymentDate(int appointmentScheduleId, LocalDate newPaymentStartDate, int remainingAdvancePayment);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule_rest_payment SET new_payment_start_date = ?2 WHERE appointment_schedule_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleRestPaymentNewPaymentDateWithoutRemainingAdvance(int appointmentScheduleId, LocalDate newPaymentStartDate);

}
