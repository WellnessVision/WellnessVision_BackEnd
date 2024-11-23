package com.example.WellnessVision.repository;

import com.example.WellnessVision.dto.RoomAlreadyBookedDatesDto;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.Room;
import com.example.WellnessVision.model.UserAppointmentBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserAppointmentBookingRepository extends JpaRepository<UserAppointmentBooking, Integer> {

    @Query(value = "SELECT COUNT(*) AS date_booking_count FROM user_appointment_booking WHERE booking_date = ?2 AND booked_appointment_id = ?1 AND booking_state = ?3", nativeQuery = true)
    Integer getTotalBookingCountPerDateForAppointmentSchedule(int booked_appointment_id, LocalDate date, String bookingState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET account_number = ?2, account_owner_name = ?3, branch_name = ?4, bank_name = ?5 WHERE booking_id = ?1", nativeQuery = true)
    void addAppointmentBookingOtherDetailsAndSaveForNu(int bookingId, String account_number,  String account_owner_name, String branch_name, String bank_name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_appointment_booking WHERE booking_id = ?1", nativeQuery = true)
    void cancelTemporarilyBookAppointmentBookingForNu(int bookingId);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booking_id = ?1", nativeQuery = true)
    UserAppointmentBooking getOneAppointmentBookingDetailsForUser(int booking_id);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE user_id = ?1 AND appointment_state = ?2 AND booking_state = ?3 ORDER BY booking_id DESC", nativeQuery = true)
    List<UserAppointmentBooking> getAppointmentBookingsDetailsOfOneUserForNu(int userId, String appointmentState, String bookingState);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booking_id = ?1", nativeQuery = true)
    UserAppointmentBooking getOneAppointmentBookingsDetailsOfUserForNu(int bookingId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET account_number = ?2, account_owner_name = ?3, branch_name = ?4, bank_name = ?5 WHERE booking_id = ?1", nativeQuery = true)
    void updateAppointmentBookingMoneyReceiptsDetailsForNU(int bookingId, String account_number,  String account_owner_name, String branch_name, String bank_name);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booked_appointment_id = ?1 AND booking_date = ?2 AND participant_id LIKE ?3", nativeQuery = true)
    List<UserAppointmentBooking> getAllParticipantDetailsForAppointmentScheduleForHp(int appointmentId, LocalDate today, String modifiedSearchCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET participant_state = ?2, appointment_state = ?3 WHERE booking_id = ?1", nativeQuery = true)
    void updateAppointmentScheduleParticipationStateForHp(int bookingId, String participantState, String appointmentState);

    @Query(value = "SELECT COUNT(*) AS date_user_booking_count FROM user_appointment_booking WHERE booking_date = ?3 AND booked_appointment_id = ?1 AND user_id = ?2", nativeQuery = true)
    Integer checkUserAppointmentBookingCountOfDateAndAppointmentId(int booked_appointment_id, int userId, LocalDate date);

    @Query(value = "SELECT COUNT(*) AS check_user_appointment_booking_state FROM user_appointment_booking WHERE booking_id = ?1 AND participant_state = ?2 AND appointment_state = ?3", nativeQuery = true)
    Integer CheckUserAppointmentBookingState(int bookingId, String participantState, String appointmentState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET booking_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updateAppointmentBookingStateForNu(int bookingId, String bookingState);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booked_appointment_id = ?1 AND booking_state = ?2 AND appointment_state = ?3 AND booking_date >= ?4 AND booking_date < ?5", nativeQuery = true)
    List<UserAppointmentBooking> getBookedParticipantDetailsForAppointmentScheduleForHp(int appointmentId, String bookingState, String appointmentState, LocalDate newPaymentStartDate, LocalDate today);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booked_appointment_id = ?1 AND booking_state = ?2 AND booking_date = ?3 AND participant_state = ?4 AND appointment_state = ?5", nativeQuery = true)
    List<UserAppointmentBooking> getBookedDetailsPerDateAppointmentScheduleForHp(int appointmentId, String bookingState, LocalDate date, String participantState, String appointmentState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET appointment_state = ?2 WHERE booking_id = ?1", nativeQuery = true)
    void updateAppointmentStateToCancelForNu(int bookingId, String appointmentState);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booked_appointment_id = ?1 AND booking_state = ?2 AND booking_date >= ?3 AND booking_date <= ?4 AND participant_state = ?5 AND appointment_state = ?6", nativeQuery = true)
    List<UserAppointmentBooking> getBookedDetailsWithInTimePeriodAppointmentScheduleForHp(int appointmentId, String bookingState, LocalDate startDate, LocalDate endDate, String participantState, String appointmentState);

    @Query(value = "SELECT * FROM user_appointment_booking WHERE booked_appointment_id = ?1 AND booking_state = ?2 AND booking_date >= ?3 AND participant_state = ?4 AND appointment_state = ?5", nativeQuery = true)
    List<UserAppointmentBooking> getBookedDetailsWithStartDateAppointmentScheduleForHp(int appointmentId, String bookingState, LocalDate startDate, String participantState, String appointmentState);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_appointment_booking SET appointment_state = ?3 WHERE booked_appointment_id = ?1 AND booking_date = ?2", nativeQuery = true)
    void changeTheAppointmentStatePerDayForNu(int appointmentId, LocalDate date, String appointmentState);

    @Query(value = "SELECT DISTINCT uABooking.booking_date FROM appointment_schedule AS aShe JOIN user_appointment_booking AS uABooking ON aShe.appointment_id = uABooking.booked_appointment_id WHERE aShe.room_id = ?1 AND uABooking.booking_date >= ?2 AND uABooking.booking_state = ?3 AND uABooking.appointment_state = ?4", nativeQuery = true)
    List<Object[]> getAlreadyBookedDatesOfOneRoomForAppointmentManager(String roomId, LocalDate minDate, String bookingState, String appointmentState);

    @Query(value = "SELECT COUNT(*) AS checkAppointmentBookingCount FROM appointment_schedule AS aShe JOIN user_appointment_booking AS uABooking ON aShe.appointment_id = uABooking.booked_appointment_id WHERE aShe.room_id = ?1 AND uABooking.booking_date >= ?2 AND uABooking.booking_date <= ?3 AND uABooking.booking_state = ?4 AND uABooking.appointment_state = ?5", nativeQuery = true)
    Integer checkAndSetMaintenanceDateOfRoomsForAppointmentManager(String roomId, LocalDate startDate, LocalDate endDate, String bookingState, String appointmentState);

    @Query(value = "SELECT COUNT(*) AS checkAppointmentBookingCount FROM appointment_schedule AS aShe JOIN user_appointment_booking AS uABooking ON aShe.appointment_id = uABooking.booked_appointment_id WHERE aShe.room_id = ?1 AND uABooking.booking_date >= ?2 AND uABooking.booking_state = ?3 AND uABooking.appointment_state = ?4", nativeQuery = true)
    Integer checkAndSetUnavailableDateOfRoomsForAppointmentManager(String roomId, LocalDate unavailableDate, String bookingState, String appointmentState);

}

