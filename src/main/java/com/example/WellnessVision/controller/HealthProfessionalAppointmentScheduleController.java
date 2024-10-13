package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.*;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.AppointmentScheduleRestPaymentService;
import com.example.WellnessVision.service.HealthProfessionalAppointmentScheduleService;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class HealthProfessionalAppointmentScheduleController {

    @Autowired
    private HealthProfessionalAppointmentScheduleService healthProfessionalAppointmentScheduleService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AppointmentScheduleRestPaymentService appointmentScheduleRestPaymentService;

    @GetMapping("/viewAllAppointmentScheduleForHp")
    public List<AppointmentScheduleDto> viewAllAppointmentScheduleForHp(@RequestParam("hpId") int hpId) throws IOException {
        return healthProfessionalAppointmentScheduleService.viewAllAppointmentScheduleForHp(hpId);
    }

    @GetMapping("/viewOneAppointmentScheduleDetailsForHp")
    public AppointmentScheduleDto viewOneAppointmentScheduleDetailsForHp(@RequestParam("appointmentId") int appointmentId) throws IOException {
        return healthProfessionalAppointmentScheduleService.viewOneAppointmentScheduleDetailsForHp(appointmentId);
    }

    @PutMapping("/updateAppointmentScheduleDailyStateForHp")
    public void updateAppointmentScheduleDailyStateForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("appointmentState") String appointmentState) throws IOException {
        healthProfessionalAppointmentScheduleService.updateAppointmentScheduleDailyStateForHp(appointmentId, appointmentState);
    }

    @PutMapping("/updateAppointmentScheduleMoneyReceiptsDetailsForHP")
    public void updateAppointmentScheduleMoneyReceiptsDetailsForHP(@RequestParam("appointmentId") int appointmentId, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        healthProfessionalAppointmentScheduleService.updateAppointmentScheduleMoneyReceiptsDetailsForHP(appointmentId, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/getAllAvailableRoomsForOneRoomTypeForHp")
    public List<Room> getAllAvailableRoomsForOneRoomTypeForHp(@RequestParam("roomType") String roomType) throws IOException {
        return healthProfessionalAppointmentScheduleService.getAllAvailableRoomsForOneRoomTypeForHp(roomType);
    }

    @GetMapping("/checkAvailableRoomsForAppointmentForHp")
    public List<HallBookingTimeSlots> checkAvailableRoomsForAppointmentForHp(@RequestParam("roomId") String roomId, @RequestParam("mon_day") int mon_day, @RequestParam("tue_day") int tue_day, @RequestParam("wed_day") int wed_day, @RequestParam("thu_day") int thu_day, @RequestParam("fri_day") int fri_day, @RequestParam("sat_day") int sat_day, @RequestParam("sun_day") int sun_day) throws IOException {
        return healthProfessionalAppointmentScheduleService.checkAvailableRoomsForAppointmentForHp(roomId,mon_day,tue_day,wed_day,thu_day,fri_day,sat_day,sun_day);
    }

    @GetMapping("/temporarilyBookingRoomForAppointmentForHp")
    public HallAvailability temporarilyBookingRoomForAppointmentForHp(@RequestParam("roomId") String roomId, @RequestParam("mon_day") int mon_day, @RequestParam("tue_day") int tue_day, @RequestParam("wed_day") int wed_day, @RequestParam("thu_day") int thu_day, @RequestParam("fri_day") int fri_day, @RequestParam("sat_day") int sat_day, @RequestParam("sun_day") int sun_day, @RequestParam("startTime") int startTime, @RequestParam("endTime") int endTime, @RequestParam("duration") int duration, @RequestParam("hpId") int hpId)
    {
        return healthProfessionalAppointmentScheduleService.temporarilyBookingRoomForAppointmentForHp(roomId, mon_day, tue_day, wed_day, thu_day, fri_day, sat_day, sun_day, startTime, endTime, duration, hpId);
    }

    @PutMapping("/deleteTemporarilyBookedRoomForAppointmentForHp")
    public void deleteTemporarilyBookedRoomForAppointmentForHp(@RequestBody EventID eventID)
    {
        healthProfessionalAppointmentScheduleService.deleteTemporarilyBookedRoomForAppointmentForHp(eventID.getEvent_id());
    }

    @PostMapping("/updateAppointmentScheduleOtherDetailsForHp")
    public void updateAppointmentScheduleOtherDetailsForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("totalRoomCharge") int totalRoomCharge, @RequestParam("advance_percentage") double advance_percentage, @RequestParam("advance_payment") int advance_payment, @RequestParam("hpId") int hpId, @RequestParam("title") String title, @RequestParam("maxPatientCount") int maxPatientCount, @RequestParam("ticketPrice") int ticketPrice, @RequestParam("accountNumber") String accountNumber, @RequestParam("accountHolderName") String accountHolderName, @RequestParam("branch") String branch, @RequestParam("bank") String bank) {
        AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
        appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
        appointmentSchedulePayment.setPayment_date(LocalDate.now());
        appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
        appointmentSchedulePayment.setAmount(advance_payment);
        appointmentSchedulePayment.setHp_id(hpId);
        appointmentSchedulePayment.setPayment_state("Payment");
        appointmentSchedulePayment.setPayment_description("Create a New Appointment Schedule");
        appointmentSchedulePayment.setAppointment_schedule_state("Available");
        int payment_id = healthProfessionalAppointmentScheduleService.appointmentSchedulePaymentSave(appointmentSchedulePayment);
        Notification notification = new Notification(
                hpId,
                "New Appointment Schedule",
                "AppointmentId : " + appointmentId + " appointment schedule successfully created. Total daily room charge : Rs."+ totalRoomCharge + "/=. You successfully paid Advance payment : Rs." + advance_payment + "/=. You can view more details about this appointment schedule under my appointment schedule category",
                "Unread",
                LocalDateTime.now()
        );
        notificationService.createNewNotificationForAllUsers(notification);
        healthProfessionalAppointmentScheduleService.updateAppointmentScheduleOtherDetailsForHp(appointmentId, totalRoomCharge, advance_percentage, advance_payment, payment_id, title, maxPatientCount, ticketPrice, accountNumber, accountHolderName, branch, bank);
        AppointmentScheduleRestPayment appointmentScheduleRestPayment = new AppointmentScheduleRestPayment(appointmentId, LocalDate.now(), advance_payment);
        appointmentScheduleRestPaymentService.createNewAppointmentScheduleRestPaymentForHp(appointmentScheduleRestPayment);
    }

    @GetMapping("/getAllParticipantDetailsForAppointmentScheduleForHp")
    public List<ViewPhysicalEventParticipationDetails> getAllParticipantDetailsForAppointmentScheduleForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("searchCode") String searchCode)
    {
        return healthProfessionalAppointmentScheduleService.getAllParticipantDetailsForAppointmentScheduleForHp(appointmentId, searchCode);
    }

    @PutMapping("/updateAppointmentScheduleParticipationStateForHp")
    public void updateAppointmentScheduleParticipationStateForHp(@RequestParam("bookingId") int bookingId, @RequestParam("participantState") String participantState)
    {
        healthProfessionalAppointmentScheduleService.updateAppointmentScheduleParticipationStateForHp(bookingId, participantState);
    }

    @GetMapping("/calculateRestPaymentTillLastPaymentDateToYesterdayForAppointmentSchedule")
    public AppointmentRemainingPaymentState calculateRestPaymentTillLastPaymentDateToYesterdayForAppointmentSchedule(@RequestParam("appointmentId") int appointmentId)
    {
        return healthProfessionalAppointmentScheduleService.calculateRestPaymentTillLastPaymentDateToYesterdayForAppointmentSchedule(appointmentId);
    }

    @PutMapping("/canselAppointmentScheduleAvailabilityPerDateForHp")
    public void canselAppointmentScheduleAvailabilityPerDateForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("hpId") int hpId, @RequestParam("remainingPayment") int remainingPayment, @RequestParam("remainingPaymentState") String remainingPaymentState, @RequestParam("newPaymentStartDate") LocalDate newPaymentStartDate, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate)
    {
        healthProfessionalAppointmentScheduleService.canselAppointmentScheduleAvailabilityPerDateForHp(appointmentId, hpId, remainingPayment, remainingPaymentState, newPaymentStartDate, startDate, endDate);
    }

    @PutMapping("/setTheAppointmentScheduleAvailableTimePeriodForHp")
    public void setTheAppointmentScheduleAvailableTimePeriodForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("hpId") int hpId, @RequestParam("remainingPayment") int remainingPayment, @RequestParam("remainingPaymentState") String remainingPaymentState, @RequestParam("newPaymentStartDate") LocalDate newPaymentStartDate, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate, @RequestParam("unavailableTimeStartDate") LocalDate unavailableTimeStartDate, @RequestParam("unavailableTimeEndDate") LocalDate unavailableTimeEndDate, @RequestParam("unavailableType") String unavailableType)
    {
        healthProfessionalAppointmentScheduleService.setTheAppointmentScheduleAvailableTimePeriodForHp(appointmentId, hpId, remainingPayment, remainingPaymentState, newPaymentStartDate, startDate, endDate, unavailableTimeStartDate, unavailableTimeEndDate, unavailableType);
    }

    @PutMapping("/setTheAppointmentScheduleAvailableTimeWithOnlyStartDateForHp")
    public void setTheAppointmentScheduleAvailableTimeWithOnlyStartDateForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("hpId") int hpId, @RequestParam("remainingPayment") int remainingPayment, @RequestParam("remainingPaymentState") String remainingPaymentState, @RequestParam("newPaymentStartDate") LocalDate newPaymentStartDate, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate, @RequestParam("unavailableTimeStartDate") LocalDate unavailableTimeStartDate, @RequestParam("unavailableType") String unavailableType)
    {
        healthProfessionalAppointmentScheduleService.setTheAppointmentScheduleAvailableTimePeriodForHp(appointmentId, hpId, remainingPayment, remainingPaymentState, newPaymentStartDate, startDate, endDate, unavailableTimeStartDate, null, unavailableType);
    }

    @GetMapping("/calculateRestPaymentWhenDeleteAppointmentScheduleForHp")
    public AppointmentRemainingPaymentState calculateRestPaymentWhenDeleteAppointmentScheduleForHp(@RequestParam("appointmentId") int appointmentId)
    {
        return healthProfessionalAppointmentScheduleService.calculateRestPaymentWhenDeleteAppointmentScheduleForHp(appointmentId);
    }

    @PutMapping("/deleteTheAppointmentScheduleForHp")
    public void deleteTheAppointmentScheduleForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("hpId") int hpId, @RequestParam("remainingPayment") int remainingPayment, @RequestParam("remainingPaymentState") String remainingPaymentState, @RequestParam("remainingAdvancePayment") int remainingAdvancePayment, @RequestParam("newPaymentStartDate") LocalDate newPaymentStartDate, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate)
    {
        healthProfessionalAppointmentScheduleService.deleteTheAppointmentScheduleForHp(appointmentId, hpId, remainingPayment, remainingPaymentState, remainingAdvancePayment, newPaymentStartDate, startDate, endDate);
    }

    @PutMapping("/closeAppointmentBookingForHp")
    public void closeAppointmentBookingForHp(@RequestParam("appointmentId") int appointmentId)
    {
        healthProfessionalAppointmentScheduleService.closeAppointmentBookingForHp(appointmentId);
    }

    @PutMapping("/clearAppointmentScheduleUnavailableDaysForHp")
    public void clearAppointmentScheduleUnavailableDaysForHp(@RequestParam("appointmentId") int appointmentId)
    {
        healthProfessionalAppointmentScheduleService.clearAppointmentScheduleUnavailableDaysForHp(appointmentId);
    }

    @PutMapping("/automaticallyUpdateTheAppointmentDalyState")
    public void automaticallyUpdateTheAppointmentDalyState()
    {
        healthProfessionalAppointmentScheduleService.automaticallyUpdateTheAppointmentDalyState();
    }

    @PutMapping("/getTheAppointmentProfitOrCleanTheRemainingPaymentForHp")
    public void getTheAppointmentProfitOrCleanTheRemainingPaymentForHp(@RequestParam("appointmentId") int appointmentId, @RequestParam("hpId") int hpId, @RequestParam("remainingPayment") int remainingPayment, @RequestParam("remainingPaymentState") String remainingPaymentState, @RequestParam("newPaymentStartDate") LocalDate newPaymentStartDate, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate)
    {
        healthProfessionalAppointmentScheduleService.getTheAppointmentProfitOrCleanTheRemainingPaymentForHp(appointmentId, hpId, remainingPayment, remainingPaymentState, newPaymentStartDate, startDate, endDate);
    }

}
