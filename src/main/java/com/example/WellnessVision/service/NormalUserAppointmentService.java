package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.AppointmentNumberAndBookingState;
import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.dto.HallBookingTimeSlots;
import com.example.WellnessVision.dto.NormalUserFineAmountDto;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class NormalUserAppointmentService {

    @Autowired
    private HealthProfessionalAppointmentScheduleRepository healthProfessionalAppointmentScheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppointmentBookingPaymentForUserRepository appointmentBookingPaymentForUserRepository;

    @Autowired
    private UserAppointmentBookingRepository userAppointmentBookingRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public AppointmentNumberAndBookingState checkAndTemporarilyBookAppointmentBookingForNu(int appointmentId, int userId, LocalDate date) throws IOException {
        AppointmentSchedule newAppointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(appointmentId);
        int currentBookingCount = userAppointmentBookingRepository.getTotalBookingCountPerDateForAppointmentSchedule(appointmentId, date, "Booked");
        int DateBookingCount = userAppointmentBookingRepository.checkUserAppointmentBookingCountOfDateAndAppointmentId(appointmentId, userId, date);
        Room room = roomRepository.getOneRoomDetailsUsingRoomId(newAppointmentSchedule.getRoomId());
        AppointmentNumberAndBookingState appointmentNumberAndBookingState = new  AppointmentNumberAndBookingState();
        if((newAppointmentSchedule.getStartUnavailableDate() == null || date.isBefore(newAppointmentSchedule.getStartUnavailableDate()) ||
                (newAppointmentSchedule.getEndUnavailableDate() != null && date.isAfter(newAppointmentSchedule.getEndUnavailableDate()))) &&
                !date.equals(newAppointmentSchedule.getAppointmentBookingCloseDate()) && DateBookingCount == 0 && ((room.getMaintain_start_date() == null && room.getUnavailable_date() == null) || (room.getMaintain_start_date() != null && (date.isBefore(room.getMaintain_start_date()) || date.isAfter(room.getMaintain_end_date()))) || (room.getUnavailable_date() != null && date.isBefore(room.getUnavailable_date())))){
           if(currentBookingCount < newAppointmentSchedule.getCapacity()){
               String participantId = "P/" + RandomStringGeneratorService.generateRandomString(7);
               UserAppointmentBooking newUserAppointmentBooking = new UserAppointmentBooking(
                       date,
                       LocalDateTime.now(),
                       appointmentId,
                       userId,
                       currentBookingCount + 1,
                       "Booked",
                       "Upcoming",
                       participantId,
                       "NotParticipate",
                       "null",
                       "null",
                       "null",
                       "null"
               );
               UserAppointmentBooking savedUserAppointmentBooking = userAppointmentBookingRepository.save(newUserAppointmentBooking);
               appointmentNumberAndBookingState.setAppointmentNumber(currentBookingCount + 1);
               appointmentNumberAndBookingState.setBookingId(savedUserAppointmentBooking.getBookingId());
               appointmentNumberAndBookingState.setBookingSate("Booked");
           }else {
               appointmentNumberAndBookingState.setAppointmentNumber(0);
               appointmentNumberAndBookingState.setBookingId(0);
               appointmentNumberAndBookingState.setBookingSate("All appointment slots for the day are fully booked");
           }
        }else {
            appointmentNumberAndBookingState.setAppointmentNumber(0);
            appointmentNumberAndBookingState.setBookingId(0);
            if (DateBookingCount != 0) {
                appointmentNumberAndBookingState.setBookingSate("You already have an appointment for the selected day");
            } else {
                appointmentNumberAndBookingState.setBookingSate("The selected day is not available or Appointment room under maintenance");
            }
        }

        return  appointmentNumberAndBookingState;
    }

    public void addAppointmentBookingOtherDetailsAndSaveForNu(int bookingId, int userId, int paidAmount, String account_number, String account_owner_name, String branch_name, String bank_name) {
        userAppointmentBookingRepository.addAppointmentBookingOtherDetailsAndSaveForNu(bookingId, account_number, account_owner_name, branch_name, bank_name);
        UserAppointmentBooking userAppointmentBooking = userAppointmentBookingRepository.getOneAppointmentBookingDetailsForUser(bookingId);
        AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = new AppointmentBookingPaymentForUser(
                bookingId,
                userId,
                LocalDateTime.now(),
                paidAmount,
                "Payment",
                "Make an appointment booking"
        );
        appointmentBookingPaymentForUserRepository.save(appointmentBookingPaymentForUser);

        Notification notification = new Notification(
                userId,
                "Make an appointment",
                "You create an new appointment booking and your participant id: " + userAppointmentBooking.getParticipantId() +
                        ", Booking fee: Rs." + paidAmount +"/=. Your appointment is on "+ userAppointmentBooking.getBookingDate() + ". You can view more details under upcoming appointment category.",
                "Unread",
                LocalDateTime.now()
        );
        notificationRepository.save(notification);

    }

    public void cancelTemporarilyBookAppointmentBookingForNu(int bookingId) {
        userAppointmentBookingRepository.cancelTemporarilyBookAppointmentBookingForNu(bookingId);
    }

    public List<UserAppointmentBooking> getAppointmentBookingsDetailsOfOneUserForNu(int userId, String appointmentState) {
        return userAppointmentBookingRepository.getAppointmentBookingsDetailsOfOneUserForNu(userId, appointmentState, "Booked");
    }

    public UserAppointmentBooking getOneAppointmentBookingsDetailsOfUserForNu(int bookingId) {
        return userAppointmentBookingRepository.getOneAppointmentBookingsDetailsOfUserForNu(bookingId);
    }

    public void updateAppointmentBookingMoneyReceiptsDetailsForNU(int bookingId, String account_number, String account_owner_name, String branch_name, String bank_name) {
        userAppointmentBookingRepository.updateAppointmentBookingMoneyReceiptsDetailsForNU(bookingId, account_number, account_owner_name, branch_name, bank_name);
    }

    public NormalUserFineAmountDto getAppointmentBookingFineAmountForNU(int bookingId) {
        int UserAppointmentBookingState = userAppointmentBookingRepository.CheckUserAppointmentBookingState(bookingId, "NotParticipate", "Upcoming");
        UserAppointmentBooking userAppointmentBooking = userAppointmentBookingRepository.getOneAppointmentBookingDetailsForUser(bookingId);
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(userAppointmentBooking.getBookedAppointmentId());
        if(UserAppointmentBookingState == 1) {
            LocalDate appointmentBookingDate = userAppointmentBooking.getBookingDate();
            LocalDate twoDaysBeforeDate = appointmentBookingDate.minusDays(2);
            LocalDate today = LocalDate.now();
            int bookingFee = appointmentSchedule.getBookingPrice();
            int fineAmount;
            double finePercentage;
            String fineAmountDetails;
            int depositAmount;
            String twoDaysBeforeState;
            if (today.isBefore(twoDaysBeforeDate)) {
                twoDaysBeforeState = "Yes";
                finePercentage = 15.00;
                fineAmount = (int) (bookingFee * 0.15);
                depositAmount = bookingFee - fineAmount;
                fineAmountDetails = "You are deleting the appointment booking two days before the booking date. " +
                        "So your fine amount is 15% of the full booking payment. After deducting the fine " +
                        "from your booking payment, the remaining amount will be credited to your bank account.";
            } else {
                twoDaysBeforeState = "No";
                finePercentage = 50.00;
                fineAmount = (int) (bookingFee * 0.50);
                depositAmount = bookingFee - fineAmount;
                fineAmountDetails = "You are deleting the appointment booking with less than two days left for the booking date. " +
                        "So your fine amount is 50% of the full booking payment. After deducting the fine " +
                        "from your booking payment, the remaining amount will be credited to your bank account.";
            }

            NormalUserFineAmountDto normalUserFineAmountDto = new NormalUserFineAmountDto(
                    today,
                    appointmentBookingDate,
                    twoDaysBeforeDate,
                    bookingFee,
                    fineAmount,
                    depositAmount,
                    finePercentage,
                    fineAmountDetails,
                    twoDaysBeforeState
            );

            return normalUserFineAmountDto;
        }else {
            return null;
        }
    }

    public void deleteAppointmentBookingForNU(int user_id, int booking_id, int fineAmount, int depositAmount, String twoDaysBeforeState){

        userAppointmentBookingRepository.updateAppointmentBookingStateForNu(booking_id, "Canceled");
        UserAppointmentBooking userAppointmentBooking = userAppointmentBookingRepository.getOneAppointmentBookingDetailsForUser(booking_id);

        AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = new AppointmentBookingPaymentForUser();
        appointmentBookingPaymentForUser.setAmount(fineAmount);
        appointmentBookingPaymentForUser.setAppointmentBookingId(booking_id);
        appointmentBookingPaymentForUser.setPaymentDate(LocalDateTime.now());
        appointmentBookingPaymentForUser.setPaymentDescription("Canceled the appointment booking and Receipts your deposit amount after deducting the fine from your booking payment");
        appointmentBookingPaymentForUser.setPaymentState("Receipts");
        appointmentBookingPaymentForUser.setUserId(user_id);
        appointmentBookingPaymentForUserRepository.save(appointmentBookingPaymentForUser);

        Notification notification;
        if(Objects.equals(twoDaysBeforeState, "Yes")){
            notification = new Notification(
                    user_id,
                    "Delete Appointment Booking",
                    "You deleted the Booking Id : " + booking_id + " appointment booking two days before the booking date. " +
                            "So your fine amount is 15% of the booking payment. Booking payment : Rs." + (fineAmount + depositAmount) +
                            "/=, Fine amount : Rs." + fineAmount + "/=, deposit amount : Rs." + depositAmount + "/= After deducting the fine " +
                            "from your booking payment, the remaining amount (deposit amount) will be credited to Account number : " + userAppointmentBooking.getAccountNumber() + ", Account holder name : " + userAppointmentBooking.getAccountOwnerName() +
                            ", Branch name : " + userAppointmentBooking.getBranchName() + ", Bank name : " + userAppointmentBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
        }else {
            notification = new Notification(
                    user_id,
                    "Delete Appointment Booking",
                    "You deleted the Booking Id : " + booking_id + " appointment booking with less than two days left for the booking date. " +
                            "So your fine amount is 50% of the booking payment. Booking payment : Rs." + (fineAmount + depositAmount) +
                            "/=, Fine amount : Rs." + fineAmount + "/=, deposit amount : Rs." + depositAmount + "/= After deducting the fine " +
                            "from your booking payment, the remaining amount (deposit amount) will be credited to Account number : " + userAppointmentBooking.getAccountNumber() + ", Account holder name : " + userAppointmentBooking.getAccountOwnerName() +
                            ", Branch name : " + userAppointmentBooking.getBranchName() + ", Bank name : " + userAppointmentBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
        }
        notificationRepository.save(notification);

    }

}