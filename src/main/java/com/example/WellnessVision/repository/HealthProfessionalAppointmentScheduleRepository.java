package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.AppointmentSchedule;
import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.model.PhysicalEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HealthProfessionalAppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Integer> {

    @Query(value = "SELECT * FROM appointment_schedule WHERE hp_id = ?1 ORDER BY appointment_id DESC", nativeQuery = true)
    List<AppointmentSchedule> viewAllAppointmentScheduleForHp(int hpId);
    @Query(value = "SELECT * FROM appointment_schedule WHERE appointment_id = ?1", nativeQuery = true)
    AppointmentSchedule viewOneAppointmentScheduleDetailsForHp(int appointmentId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET daily_state = ?2 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleDailyStateForHp(int appointmentId, String appointmentState);
    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET account_number = ?2, account_owner_name = ?3, branch_name = ?4, bank_name = ?5 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleMoneyReceiptsDetailsForHP(int appointmentId, String account_number,  String account_owner_name, String branch_name, String bank_name);

    @Query(value = "SELECT * FROM appointment_schedule WHERE room_id = ?1 AND (mon_day = ?2 OR tue_day = ?3 OR wed_day = ?4 OR thu_day = ?5 OR fri_day = ?6 OR sat_day = ?7 OR sun_day = ?8) ORDER BY start_time ASC, end_time ASC", nativeQuery = true)
    List<AppointmentSchedule> checkAvailableRoomsForAppointmentForHp(String roomId,int mon_day,int tue_day,int wed_day,int thu_day,int fri_day,int sat_day,int sun_day);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM appointment_schedule WHERE appointment_id = ?1", nativeQuery = true)
    void deleteTemporarilyBookedRoomForAppointmentForHp(int appointment_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET total_hall_charge = ?2, advance_percentage = ?3, advance_payment = ?4, payment_id = ?5, title = ?6, capacity = ?7, booking_price = ?8, account_number = ?9, account_owner_name = ?10, branch_name = ?11, bank_name = ?12 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleOtherDetailsForHp(int appointmentId, int totalRoomCharge, double advance_percentage, int advance_payment, int payment_id, String title, int maxPatientCount, int ticketPrice, String accountNumber, String accountHolderName, String branch, String bank);

    @Query(value = "SELECT * FROM appointment_schedule WHERE appointment_id = ?1", nativeQuery = true)
    AppointmentSchedule getAppointmentScheduleMaxBookingCountAndUnavailableDates(int appointmentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET daily_state = ?2, daly_unavailable_date = ?3 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleDailyStateToUnavailableAndUnavailableDateForHp(int appointmentId, String appointmentState, LocalDate dailyUnavailableDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET daily_state = ?2, start_unavailable_date = ?3, end_unavailable_date = ?4, daly_unavailable_date = ?5 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleUnavailableTimePeriodForHp(int appointmentId, String appointmentState, LocalDate startDate, LocalDate endDate, LocalDate dailyUnavailableDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET start_unavailable_date = ?2, end_unavailable_date = ?3 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleUnavailableTimePeriodForHpWithOutDalyUnavailableDate(int appointmentId, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM appointment_schedule WHERE appointment_id = ?1", nativeQuery = true)
    void deleteAppointmentScheduleForHp(int appointment_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET appointment_booking_close_date = ?2 WHERE appointment_id = ?1", nativeQuery = true)
    void updateAppointmentBookingCloseDateForHp(int appointmentId, LocalDate appointmentBookingCloseDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointment_schedule SET start_unavailable_date = ?2, end_unavailable_date = ?2 WHERE appointment_id = ?1", nativeQuery = true)
    void clearAppointmentScheduleUnavailableDaysForHp(int appointmentId, LocalDate appointmentUnavailableDays);

    @Query(value = "SELECT * FROM appointment_schedule", nativeQuery = true)
    List<AppointmentSchedule> getAllAppointmentSchedulesForHp();

    @Query(value = "SELECT * FROM appointment_schedule WHERE room_id = ?1 ORDER BY appointment_id DESC", nativeQuery = true)
    List<AppointmentSchedule> getAppointmentSchedulesOfRoomForAppointmentManager(String roomId);

}
