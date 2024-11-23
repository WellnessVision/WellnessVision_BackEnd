package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.*;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.HealthProfessionalAppointmentScheduleService;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.NormalUserAppointmentService;
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
public class NormalUserAppointmentController {

    @Autowired
    private HealthProfessionalAppointmentScheduleService healthProfessionalAppointmentScheduleService;

    @Autowired
    private NormalUserAppointmentService normalUserAppointmentService;

    @GetMapping("/checkAndTemporarilyBookAppointmentBookingForNu")
    public AppointmentNumberAndBookingState checkAndTemporarilyBookAppointmentBookingForNu(@RequestParam("appointmentId") int appointmentId, @RequestParam("userId") int userId, @RequestParam("date") LocalDate date) throws IOException {
        return normalUserAppointmentService.checkAndTemporarilyBookAppointmentBookingForNu(appointmentId,userId, date);
    }

    @PutMapping("/cancelTemporarilyBookAppointmentBookingForNu")
    public void cancelTemporarilyBookAppointmentBookingForNu(@RequestBody EventID eventID)
    {
        normalUserAppointmentService.cancelTemporarilyBookAppointmentBookingForNu(eventID.getEvent_id());
    }

    @PutMapping("/addAppointmentBookingOtherDetailsAndSaveForNu")
    public void addAppointmentBookingOtherDetailsAndSaveForNu(@RequestParam("bookingId") int bookingId, @RequestParam("userId") int userId, @RequestParam("paidAmount") int paidAmount, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        normalUserAppointmentService.addAppointmentBookingOtherDetailsAndSaveForNu(bookingId, userId, paidAmount, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/getAppointmentBookingsDetailsOfOneUserForNu")
    public List<UserAppointmentBooking> getAppointmentBookingsDetailsOfOneUserForNu(@RequestParam("userId") int userId, @RequestParam("appointmentState") String appointmentState) throws IOException {
        return normalUserAppointmentService.getAppointmentBookingsDetailsOfOneUserForNu(userId, appointmentState);
    }

    @GetMapping("/getOneAppointmentBookingsDetailsOfUserForNu")
    public UserAppointmentBooking getOneAppointmentBookingsDetailsOfUserForNu(@RequestParam("bookingId") int bookingId) throws IOException {
        return normalUserAppointmentService.getOneAppointmentBookingsDetailsOfUserForNu(bookingId);
    }

    @PutMapping("/updateAppointmentBookingMoneyReceiptsDetailsForNU")
    public void updateAppointmentBookingMoneyReceiptsDetailsForNU(@RequestParam("bookingId") int bookingId, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        normalUserAppointmentService.updateAppointmentBookingMoneyReceiptsDetailsForNU(bookingId, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/getAppointmentBookingFineAmountForNU")
    public NormalUserFineAmountDto getAppointmentBookingFineAmountForNU(@RequestParam("bookingId") int bookingId) {
        return normalUserAppointmentService.getAppointmentBookingFineAmountForNU(bookingId);
    }

    @PutMapping("/deleteAppointmentBookingForNU")
    public void deletePhysicalEventBookingForNU(@RequestParam("userId") int user_id,  @RequestParam("bookingId") int booking_id, @RequestParam("fineAmount") int fineAmount, @RequestParam("depositAmount") int depositAmount, @RequestParam("twoDaysBeforeState") String twoDaysBeforeState) {
        normalUserAppointmentService.deleteAppointmentBookingForNU(user_id, booking_id, fineAmount, depositAmount, twoDaysBeforeState);
    }

}
