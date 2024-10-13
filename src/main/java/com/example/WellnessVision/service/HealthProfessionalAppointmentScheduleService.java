package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.*;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class HealthProfessionalAppointmentScheduleService {

    @Autowired
    private HealthProfessionalAppointmentScheduleRepository healthProfessionalAppointmentScheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppointmentSchedulePaymentRepository appointmentSchedulePaymentRepository;

    @Autowired
    private UserAppointmentBookingRepository userAppointmentBookingRepository;

    @Autowired
    private NormalUserRegisterRepository normalUserRegisterRepository;

    @Autowired
    private AppointmentBookingPaymentForUserRepository appointmentBookingPaymentForUserRepository;

    @Autowired
    private AppointmentScheduleRestPaymentRepository appointmentScheduleRestPaymentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private DeleteAppointmentScheduleRepository deleteAppointmentScheduleRepository;

    public List<AppointmentScheduleDto> viewAllAppointmentScheduleForHp(int hpId) throws IOException {
        List<AppointmentSchedule> appointmentSchedules =  healthProfessionalAppointmentScheduleRepository.viewAllAppointmentScheduleForHp(hpId);
        List<AppointmentScheduleDto> AppointmentScheduleDtoList = new ArrayList<>();
        for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
            String appointmentBookingCloseState;
            if(appointmentSchedule.getAppointmentBookingCloseDate() != null && (appointmentSchedule.getAppointmentBookingCloseDate().isEqual(LocalDate.now()))){
                appointmentBookingCloseState = "Yes";
            }else {
                appointmentBookingCloseState = "No";
            }
            AppointmentScheduleDto appointmentScheduleDto = new AppointmentScheduleDto(
                    appointmentSchedule.getAppointmentId(),
                    appointmentSchedule.getRoomId(),
                    appointmentSchedule.getTitle(),
                    appointmentSchedule.getRoomType(),
                    appointmentSchedule.getSunDay(),
                    appointmentSchedule.getMonDay(),
                    appointmentSchedule.getTueDay(),
                    appointmentSchedule.getWedDay(),
                    appointmentSchedule.getThuDay(),
                    appointmentSchedule.getFriDay(),
                    appointmentSchedule.getSatDay(),
                    appointmentSchedule.getStartTime(),
                    appointmentSchedule.getEndTime(),
                    appointmentSchedule.getDuration(),
                    appointmentSchedule.getCapacity(),
                    appointmentSchedule.getBookingPrice(),
                    appointmentSchedule.getTotalHallCharge(),
                    appointmentSchedule.getAdvancePercentage(),
                    appointmentSchedule.getAdvancePayment(),
                    appointmentSchedule.getPaymentId(),
                    appointmentSchedule.getHpId(),
                    appointmentSchedule.getDailyState(),
                    appointmentSchedule.getAccountNumber(),
                    appointmentSchedule.getAccountOwnerName(),
                    appointmentSchedule.getBranchName(),
                    appointmentSchedule.getBankName(),
                    appointmentSchedule.getBookingCount(),
                    appointmentSchedule.getStartUnavailableDate(),
                    appointmentSchedule.getEndUnavailableDate(),
                    appointmentSchedule.getAppointmentBookingCloseDate(),
                    appointmentSchedule.getDalyUnavailableDate(),
                    appointmentBookingCloseState
            );
            AppointmentScheduleDtoList.add(appointmentScheduleDto);
        }

        return AppointmentScheduleDtoList;
    }

    public AppointmentScheduleDto viewOneAppointmentScheduleDetailsForHp(int appointmentId) throws IOException {
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.viewOneAppointmentScheduleDetailsForHp(appointmentId);
        String appointmentBookingCloseState;
        if(appointmentSchedule.getAppointmentBookingCloseDate() != null && (appointmentSchedule.getAppointmentBookingCloseDate().isEqual(LocalDate.now()))){
            appointmentBookingCloseState = "Yes";
        }else {
            appointmentBookingCloseState = "No";
        }
        AppointmentScheduleDto appointmentScheduleDto = new AppointmentScheduleDto(
                appointmentId,
                appointmentSchedule.getRoomId(),
                appointmentSchedule.getTitle(),
                appointmentSchedule.getRoomType(),
                appointmentSchedule.getSunDay(),
                appointmentSchedule.getMonDay(),
                appointmentSchedule.getTueDay(),
                appointmentSchedule.getWedDay(),
                appointmentSchedule.getThuDay(),
                appointmentSchedule.getFriDay(),
                appointmentSchedule.getSatDay(),
                appointmentSchedule.getStartTime(),
                appointmentSchedule.getEndTime(),
                appointmentSchedule.getDuration(),
                appointmentSchedule.getCapacity(),
                appointmentSchedule.getBookingPrice(),
                appointmentSchedule.getTotalHallCharge(),
                appointmentSchedule.getAdvancePercentage(),
                appointmentSchedule.getAdvancePayment(),
                appointmentSchedule.getPaymentId(),
                appointmentSchedule.getHpId(),
                appointmentSchedule.getDailyState(),
                appointmentSchedule.getAccountNumber(),
                appointmentSchedule.getAccountOwnerName(),
                appointmentSchedule.getBranchName(),
                appointmentSchedule.getBankName(),
                appointmentSchedule.getBookingCount(),
                appointmentSchedule.getStartUnavailableDate(),
                appointmentSchedule.getEndUnavailableDate(),
                appointmentSchedule.getAppointmentBookingCloseDate(),
                appointmentSchedule.getDalyUnavailableDate(),
                appointmentBookingCloseState
        );
        return appointmentScheduleDto;
    }

    public void updateAppointmentScheduleDailyStateForHp(int appointmentId, String appointmentState) throws IOException {
        healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleDailyStateForHp(appointmentId, appointmentState);
    }

    public void updateAppointmentScheduleMoneyReceiptsDetailsForHP(int appointmentId, String account_number, String account_owner_name, String branch_name, String bank_name) {
        healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleMoneyReceiptsDetailsForHP(appointmentId, account_number, account_owner_name, branch_name, bank_name);
    }

    public List<Room> getAllAvailableRoomsForOneRoomTypeForHp(String roomType) {
        return roomRepository.getAllAvailableRoomsForOneRoomTypeForHp(roomType, "Available");
    }

    public List<HallBookingTimeSlots> checkAvailableRoomsForAppointmentForHp(String roomId, int mon_day, int tue_day, int wed_day, int thu_day, int fri_day, int sat_day, int sun_day) {
        List<AppointmentSchedule> appointmentSchedules = healthProfessionalAppointmentScheduleRepository.checkAvailableRoomsForAppointmentForHp(roomId, mon_day, tue_day, wed_day, thu_day, fri_day, sat_day, sun_day);
        List<HallBookingTimeSlots> hallBookingTimeSlots = new ArrayList<>();
        if (appointmentSchedules.isEmpty()) {
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(8);
            hallBookingTimeSlot.setEndTime(18);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
            return hallBookingTimeSlots;
        }
        int FreeStart = appointmentSchedules.get(0).getStartTime();
        int FinalTime = appointmentSchedules.get(appointmentSchedules.size() - 1).getEndTime();
        int intermediateEndTime = 8;
        int flag = 1;
        if (FreeStart > 8) {
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(8);
            hallBookingTimeSlot.setEndTime(FreeStart);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }
        while (intermediateEndTime != FinalTime) {
            for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                if (appointmentSchedule.getStartTime() == FreeStart && appointmentSchedule.getEndTime() > intermediateEndTime) {
                    intermediateEndTime = appointmentSchedule.getEndTime();
                }
            }
            while (flag == 1) {
                flag = 0;
                for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                    if (appointmentSchedule.getStartTime() > FreeStart && appointmentSchedule.getStartTime() <= intermediateEndTime && appointmentSchedule.getEndTime() > intermediateEndTime) {
                        intermediateEndTime = appointmentSchedule.getEndTime();
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                        if (intermediateEndTime < appointmentSchedule.getStartTime()) {
                            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
                            hallBookingTimeSlot.setStartTime(intermediateEndTime);
                            hallBookingTimeSlot.setEndTime(appointmentSchedule.getStartTime());
                            hallBookingTimeSlots.add(hallBookingTimeSlot);
                            FreeStart = appointmentSchedule.getStartTime();
                            intermediateEndTime = appointmentSchedule.getStartTime();
                            break;
                        }
                    }
                }
            }
        }

        if (FinalTime < 18) {
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(FinalTime);
            hallBookingTimeSlot.setEndTime(18);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }

        return hallBookingTimeSlots;
    }

    public HallAvailability temporarilyBookingRoomForAppointmentForHp(String roomId, int mon_day, int tue_day, int wed_day, int thu_day, int fri_day, int sat_day, int sun_day, int startTime, int endTime, int duration, int hpId) {
        List<AppointmentSchedule> appointmentSchedules = healthProfessionalAppointmentScheduleRepository.checkAvailableRoomsForAppointmentForHp(roomId, mon_day, tue_day, wed_day, thu_day, fri_day, sat_day, sun_day);
        List<HallBookingTimeSlots> hallBookingTimeSlots = new ArrayList<>();
        Room room = roomRepository.getOneRoomDetailsUsingRoomId(roomId);
        if (appointmentSchedules.isEmpty()) {
            if (mon_day != 1) {
                mon_day = 0;
            }
            if (tue_day != 1) {
                tue_day = 0;
            }
            if (wed_day != 1) {
                wed_day = 0;
            }
            if (thu_day != 1) {
                thu_day = 0;
            }
            if (fri_day != 1) {
                fri_day = 0;
            }
            if (sat_day != 1) {
                sat_day = 0;
            }
            if (sun_day != 1) {
                sun_day = 0;
            }
            AppointmentSchedule newAppointmentSchedule = new AppointmentSchedule(
                    roomId,
                    "",
                    room.getRoomType(),
                    sun_day,
                    mon_day,
                    tue_day,
                    wed_day,
                    thu_day,
                    fri_day,
                    sat_day,
                    startTime,
                    endTime,
                    duration,
                    0,
                    0,
                    0,
                    room.getAdvancePercentage(),
                    0,
                    0,
                    hpId,
                    "Available",
                    "",
                    "",
                    "",
                    "",
                    0
            );
            AppointmentSchedule savedAppointmentSchedule = healthProfessionalAppointmentScheduleRepository.save(newAppointmentSchedule);
            HallAvailability hallAvailability = new HallAvailability();
            hallAvailability.setHall_id(roomId);
            hallAvailability.setHall_type(room.getRoomType());
            hallAvailability.setCapacity(0);
            hallAvailability.setAdvance_percentage(room.getAdvancePercentage());
            hallAvailability.setCharge(room.getCharge());
            hallAvailability.setEvent_id(savedAppointmentSchedule.getAppointmentId());
            return hallAvailability;
        }
        int FreeStart = appointmentSchedules.get(0).getStartTime();
        int FinalTime = appointmentSchedules.get(appointmentSchedules.size() - 1).getEndTime();
        int intermediateEndTime = 8;
        int flag = 1;
        if (FreeStart > 8) {
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(8);
            hallBookingTimeSlot.setEndTime(FreeStart);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }
        while (intermediateEndTime != FinalTime) {
            for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                if (appointmentSchedule.getStartTime() == FreeStart && appointmentSchedule.getEndTime() > intermediateEndTime) {
                    intermediateEndTime = appointmentSchedule.getEndTime();
                }
            }
            while (flag == 1) {
                flag = 0;
                for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                    if (appointmentSchedule.getStartTime() > FreeStart && appointmentSchedule.getStartTime() <= intermediateEndTime && appointmentSchedule.getEndTime() > intermediateEndTime) {
                        intermediateEndTime = appointmentSchedule.getEndTime();
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    for (AppointmentSchedule appointmentSchedule : appointmentSchedules) {
                        if (intermediateEndTime < appointmentSchedule.getStartTime()) {
                            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
                            hallBookingTimeSlot.setStartTime(intermediateEndTime);
                            hallBookingTimeSlot.setEndTime(appointmentSchedule.getStartTime());
                            hallBookingTimeSlots.add(hallBookingTimeSlot);
                            FreeStart = appointmentSchedule.getStartTime();
                            intermediateEndTime = appointmentSchedule.getStartTime();
                            break;
                        }
                    }
                }
            }
        }

        if (FinalTime < 18) {
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(FinalTime);
            hallBookingTimeSlot.setEndTime(18);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }

        for (HallBookingTimeSlots hallBookingTimeSlot : hallBookingTimeSlots) {
            if (startTime >= hallBookingTimeSlot.getStartTime() && endTime <= hallBookingTimeSlot.getEndTime()) {
                if (mon_day != 1) {
                    mon_day = 0;
                }
                if (tue_day != 1) {
                    tue_day = 0;
                }
                if (wed_day != 1) {
                    wed_day = 0;
                }
                if (thu_day != 1) {
                    thu_day = 0;
                }
                if (fri_day != 1) {
                    fri_day = 0;
                }
                if (sat_day != 1) {
                    sat_day = 0;
                }
                if (sun_day != 1) {
                    sun_day = 0;
                }
                AppointmentSchedule newAppointmentSchedule = new AppointmentSchedule(
                        roomId,
                        "",
                        room.getRoomType(),
                        sun_day,
                        mon_day,
                        tue_day,
                        wed_day,
                        thu_day,
                        fri_day,
                        sat_day,
                        startTime,
                        endTime,
                        duration,
                        0,
                        0,
                        0,
                        room.getAdvancePercentage(),
                        0,
                        0,
                        hpId,
                        "Available",
                        "",
                        "",
                        "",
                        "",
                        0
                );
                AppointmentSchedule savedAppointmentSchedule = healthProfessionalAppointmentScheduleRepository.save(newAppointmentSchedule);
                HallAvailability hallAvailability = new HallAvailability();
                hallAvailability.setHall_id(roomId);
                hallAvailability.setHall_type(room.getRoomType());
                hallAvailability.setCapacity(0);
                hallAvailability.setAdvance_percentage(room.getAdvancePercentage());
                hallAvailability.setCharge(room.getCharge());
                hallAvailability.setEvent_id(savedAppointmentSchedule.getAppointmentId());
                return hallAvailability;
            }
        }

        return null;
    }

    public void deleteTemporarilyBookedRoomForAppointmentForHp(int appointmentId) {
        healthProfessionalAppointmentScheduleRepository.deleteTemporarilyBookedRoomForAppointmentForHp(appointmentId);
    }

    public Integer appointmentSchedulePaymentSave(AppointmentSchedulePayment appointmentSchedulePayment) {
        AppointmentSchedulePayment savedAppointmentSchedulePayment = appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);
        return savedAppointmentSchedulePayment.getPayment_id();
    }

    public void updateAppointmentScheduleOtherDetailsForHp(int appointmentId, int totalRoomCharge, double advance_percentage, int advance_payment, int payment_id, String title, int maxPatientCount, int ticketPrice, String accountNumber, String accountHolderName, String branch, String bank) {
        healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleOtherDetailsForHp(appointmentId, totalRoomCharge, advance_percentage, advance_payment, payment_id, title, maxPatientCount, ticketPrice, accountNumber, accountHolderName, branch, bank);
    }

    public List<ViewPhysicalEventParticipationDetails> getAllParticipantDetailsForAppointmentScheduleForHp(int appointmentId, String searchCode) {
        String modifiedSearchCode = searchCode + "%";
        LocalDate today = LocalDate.now();
        List<UserAppointmentBooking> UserAppointmentBookingList = userAppointmentBookingRepository.getAllParticipantDetailsForAppointmentScheduleForHp(appointmentId, today, modifiedSearchCode);
        List<ViewPhysicalEventParticipationDetails> viewPhysicalEventParticipationDetailsList = new ArrayList<>();
        for (UserAppointmentBooking userAppointmentBooking : UserAppointmentBookingList) {
            NormalUser normalUser = normalUserRegisterRepository.getOneUserDetails(userAppointmentBooking.getUserId());
            ViewPhysicalEventParticipationDetails viewPhysicalEventParticipationDetails = new ViewPhysicalEventParticipationDetails(
                    normalUser.getUser_id(),
                    normalUser.getEmail(),
                    normalUser.getPhone(),
                    normalUser.getDistrict(),
                    normalUser.getCity(),
                    normalUser.getAddress(),
                    normalUser.getAddress2(),
                    normalUser.getFirstName(),
                    normalUser.getLastName(),
                    normalUser.getPreferences(),
                    normalUser.getProvince(),
                    normalUser.getProfilePic(),
                    userAppointmentBooking.getBookingId(),
                    userAppointmentBooking.getBookingState(),
                    userAppointmentBooking.getParticipantId(),
                    userAppointmentBooking.getParticipantState()
            );
            viewPhysicalEventParticipationDetailsList.add(viewPhysicalEventParticipationDetails);
        }

        return viewPhysicalEventParticipationDetailsList;

    }

    public void updateAppointmentScheduleParticipationStateForHp(int bookingId, String participantState) {
        if(Objects.equals(participantState, "Participated")){
            userAppointmentBookingRepository.updateAppointmentScheduleParticipationStateForHp(bookingId, participantState, "Previous");
        }else {
            userAppointmentBookingRepository.updateAppointmentScheduleParticipationStateForHp(bookingId, participantState, "Upcoming");
        }
    }

    public AppointmentRemainingPaymentState calculateRestPaymentTillLastPaymentDateToYesterdayForAppointmentSchedule(int appointmentId) {
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(appointmentId);
        LocalDate today = LocalDate.now();
        LocalDate checkLastDate;
        LocalDate AppointmentBookingCloseDate = appointmentSchedule.getAppointmentBookingCloseDate();
        AppointmentScheduleRestPayment appointmentScheduleRestPayment = appointmentScheduleRestPaymentRepository.getAppointmentScheduleRestPaymentForOneAppointmentSchedule(appointmentId);
        LocalDate newPaymentStartDate = appointmentScheduleRestPayment.getNewPaymentStartDate();
        AppointmentRemainingPaymentState appointmentRemainingPaymentState = new AppointmentRemainingPaymentState();
        int AdvancePayment = appointmentScheduleRestPayment.getRemainingAdvancePayment();
        int totalBookingDateCount = 0;
        int totalRoomChargePreDayTillNewPaymentStartDateToYesterday;
        int totalIncome = 0;
        if(AppointmentBookingCloseDate != null && AppointmentBookingCloseDate.isEqual(today)){
            checkLastDate = today.plusDays(1);
        }else {
            checkLastDate = today;
        }

        for (LocalDate date = newPaymentStartDate; date.isBefore(checkLastDate); date = date.plusDays(1)) {
            if (
                    (date.getDayOfWeek() == DayOfWeek.MONDAY && appointmentSchedule.getMonDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.TUESDAY && appointmentSchedule.getTueDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.WEDNESDAY && appointmentSchedule.getWedDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.THURSDAY && appointmentSchedule.getThuDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.FRIDAY && appointmentSchedule.getFriDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.SATURDAY && appointmentSchedule.getSatDay() == 1) ||
                    (date.getDayOfWeek() == DayOfWeek.SUNDAY && appointmentSchedule.getSunDay() == 1)
            ) {
                totalBookingDateCount++;
            }
        }

        totalRoomChargePreDayTillNewPaymentStartDateToYesterday = totalBookingDateCount * appointmentSchedule.getTotalHallCharge();
        List<UserAppointmentBooking> UserAppointmentBookingList = userAppointmentBookingRepository.getBookedParticipantDetailsForAppointmentScheduleForHp(appointmentId, "Booked", "Previous", newPaymentStartDate, checkLastDate);
        for (UserAppointmentBooking userAppointmentBooking : UserAppointmentBookingList) {
                AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = appointmentBookingPaymentForUserRepository.getOneAppointmentBookingPaymentForNU(userAppointmentBooking.getBookingId());
                totalIncome = totalIncome + appointmentBookingPaymentForUser.getAmount();
        }

        int RemainingPayment =  totalIncome - totalRoomChargePreDayTillNewPaymentStartDateToYesterday;
        int AfterDeductingAdvancePayment;

        appointmentRemainingPaymentState.setStartDate(newPaymentStartDate);
        appointmentRemainingPaymentState.setEndDate(checkLastDate.minusDays(1));
        appointmentRemainingPaymentState.setRemainingAdvance(AdvancePayment);
        appointmentRemainingPaymentState.setIncome(totalIncome);
        appointmentRemainingPaymentState.setTotalRoomCharge(totalRoomChargePreDayTillNewPaymentStartDateToYesterday);

        if(RemainingPayment < 0){
            AfterDeductingAdvancePayment = RemainingPayment + AdvancePayment;
            if(AfterDeductingAdvancePayment < 0){
                appointmentRemainingPaymentState.setRemainingPayment(-1 * AfterDeductingAdvancePayment);
                appointmentRemainingPaymentState.setRemainingPaymentState("NeedPayment");
            }else {
                appointmentRemainingPaymentState.setRemainingPayment(AfterDeductingAdvancePayment);
                appointmentRemainingPaymentState.setRemainingPaymentState("OkWithAdvance");
            }

        }else {
            appointmentRemainingPaymentState.setRemainingPayment(RemainingPayment);
            appointmentRemainingPaymentState.setRemainingPaymentState("Ok");
        }

        appointmentRemainingPaymentState.setNewPaymentStartDate(checkLastDate);

        return  appointmentRemainingPaymentState;

    }

    public void canselAppointmentScheduleAvailabilityPerDateForHp(int appointmentId, int hpId, int remainingPayment, String remainingPaymentState, LocalDate newPaymentStartDate, LocalDate startDate, LocalDate endDate) {
        healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleDailyStateToUnavailableAndUnavailableDateForHp(appointmentId, "Unavailable", newPaymentStartDate);
        if(Objects.equals(remainingPaymentState, "NeedPayment")){
            appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId,newPaymentStartDate, 0);
            AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
            appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
            appointmentSchedulePayment.setPayment_date(LocalDate.now());
            appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
            appointmentSchedulePayment.setAmount(remainingPayment);
            appointmentSchedulePayment.setHp_id(hpId);
            appointmentSchedulePayment.setPayment_state("Payment");
            appointmentSchedulePayment.setPayment_description("Pay Remaining Payment for " + startDate + " to " + endDate + " time period");
            appointmentSchedulePayment.setAppointment_schedule_state("Unavailable for " + newPaymentStartDate);
            appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);
            Notification notification = new Notification(
                    hpId,
                    "Change the Appointment State",
                    "AppointmentId : " + appointmentId + " is Unavailable for " + newPaymentStartDate + ". You paid Rs." + remainingPayment +
                            "/= payment as a remaining payment for " + startDate + " to " + endDate + " time period",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }else {
            if(Objects.equals(remainingPaymentState, "OkWithAdvance")) {
                appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId, newPaymentStartDate, remainingPayment);
            }

            Notification notification = new Notification(
                    hpId,
                    "Change the Appointment State",
                    "AppointmentId : " + appointmentId + " is Unavailable for " + newPaymentStartDate + ".",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);

        }

        List<UserAppointmentBooking> userAppointmentBookings = userAppointmentBookingRepository.getBookedDetailsPerDateAppointmentScheduleForHp(appointmentId ,"Booked", newPaymentStartDate, "NotParticipate", "Upcoming");
        for (UserAppointmentBooking userAppointmentBooking : userAppointmentBookings) {
            userAppointmentBookingRepository.updateAppointmentStateToCancelForNu(userAppointmentBooking.getBookingId(), "Canceled");
            AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = appointmentBookingPaymentForUserRepository.getOneAppointmentBookingPaymentForNU(userAppointmentBooking.getBookingId());

            AppointmentBookingPaymentForUser newAppointmentBookingPaymentForUser = new AppointmentBookingPaymentForUser();
            newAppointmentBookingPaymentForUser.setAmount(appointmentBookingPaymentForUser.getAmount());
            newAppointmentBookingPaymentForUser.setAppointmentBookingId(userAppointmentBooking.getBookingId());
            newAppointmentBookingPaymentForUser.setPaymentDate(LocalDateTime.now());
            newAppointmentBookingPaymentForUser.setPaymentDescription("Canceled the appointment booking by the health professional and your booking payment deposit to your bank account");
            newAppointmentBookingPaymentForUser.setPaymentState("Receipts");
            newAppointmentBookingPaymentForUser.setUserId(userAppointmentBooking.getUserId());
            appointmentBookingPaymentForUserRepository.save(newAppointmentBookingPaymentForUser);

            Notification notification = new Notification(
                    userAppointmentBooking.getUserId(),
                    "Cancel the Appointment",
                    "Cancel the Appointment booking Id: " + userAppointmentBooking.getBookingId() + " on " + userAppointmentBooking.getBookingDate() + " by the health professional, Your booking payment credited to Account number : " + userAppointmentBooking.getAccountNumber() + ", Account holder name : " + userAppointmentBooking.getAccountOwnerName() +
                            ", Branch name : " + userAppointmentBooking.getBranchName() + ", Bank name : " + userAppointmentBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }


    }

    public void setTheAppointmentScheduleAvailableTimePeriodForHp(int appointmentId, int hpId, int remainingPayment, String remainingPaymentState, LocalDate newPaymentStartDate, LocalDate paymentStartDate, LocalDate paymentEndDate, LocalDate unavailableTimeStartDate, LocalDate unavailableTimeEndDate, String unavailableType) {
        if(unavailableTimeStartDate.isEqual(newPaymentStartDate)){
            healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleUnavailableTimePeriodForHp(appointmentId, "Unavailable", unavailableTimeStartDate, unavailableTimeEndDate, newPaymentStartDate);
        }else {
            healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleUnavailableTimePeriodForHpWithOutDalyUnavailableDate(appointmentId, unavailableTimeStartDate, unavailableTimeEndDate);
        }
        if(Objects.equals(remainingPaymentState, "NeedPayment")){
            appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId,newPaymentStartDate, 0);
            AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
            appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
            appointmentSchedulePayment.setPayment_date(LocalDate.now());
            appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
            appointmentSchedulePayment.setAmount(remainingPayment);
            appointmentSchedulePayment.setHp_id(hpId);
            appointmentSchedulePayment.setPayment_state("Payment");
            appointmentSchedulePayment.setPayment_description("Pay Remaining Payment for " + paymentStartDate + " to " + paymentEndDate + " time period");
            if(Objects.equals(unavailableType, "OnlyStartDate")) {
                appointmentSchedulePayment.setAppointment_schedule_state("Unavailable from " + unavailableTimeStartDate + " towards.");
            }else {
                appointmentSchedulePayment.setAppointment_schedule_state("Unavailable from " + unavailableTimeStartDate + " to " + unavailableTimeEndDate+ ".");
            }
            appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);
            if(Objects.equals(unavailableType, "OnlyStartDate")){
                Notification notification = new Notification(
                        hpId,
                        "Change the Appointment State",
                        "AppointmentId : " + appointmentId + " is Unavailable from " + unavailableTimeStartDate + " towards. You paid Rs." + remainingPayment +
                                "/= payment as a remaining payment for " + paymentStartDate + " to " + paymentEndDate + " time period",
                        "Unread",
                        LocalDateTime.now()
                );
                notificationRepository.save(notification);
            }else {
                Notification notification = new Notification(
                        hpId,
                        "Change the Appointment State",
                        "AppointmentId : " + appointmentId + " is Unavailable from " + unavailableTimeStartDate + " to + " + unavailableTimeEndDate + ". You paid Rs." + remainingPayment +
                                "/= payment as a remaining payment for " + paymentStartDate + " to " + paymentEndDate + " time period",
                        "Unread",
                        LocalDateTime.now()
                );
                notificationRepository.save(notification);
            }
            } else {
            if(Objects.equals(remainingPaymentState, "OkWithAdvance")) {
                appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId, newPaymentStartDate, remainingPayment);
            }

            if(Objects.equals(unavailableType, "OnlyStartDate")){
                Notification notification = new Notification(
                        hpId,
                        "Change the Appointment State",
                        "AppointmentId : " + appointmentId + " is Unavailable from " + unavailableTimeStartDate + " towards.",
                        "Unread",
                        LocalDateTime.now()
                );
                notificationRepository.save(notification);
            }else {
                Notification notification = new Notification(
                        hpId,
                        "Change the Appointment State",
                        "AppointmentId : " + appointmentId + " is Unavailable from " + unavailableTimeStartDate + " to + " + unavailableTimeEndDate + ".",
                        "Unread",
                        LocalDateTime.now()
                );
                notificationRepository.save(notification);
            }

        }

        List<UserAppointmentBooking> userAppointmentBookings;
        if(Objects.equals(unavailableType, "OnlyStartDate")) {
            userAppointmentBookings = userAppointmentBookingRepository.getBookedDetailsWithStartDateAppointmentScheduleForHp(appointmentId, "Booked", unavailableTimeStartDate, "NotParticipate", "Upcoming");
        }else {
            userAppointmentBookings = userAppointmentBookingRepository.getBookedDetailsWithInTimePeriodAppointmentScheduleForHp(appointmentId, "Booked", unavailableTimeStartDate, unavailableTimeEndDate, "NotParticipate", "Upcoming");
        }

        for (UserAppointmentBooking userAppointmentBooking : userAppointmentBookings) {
            userAppointmentBookingRepository.updateAppointmentStateToCancelForNu(userAppointmentBooking.getBookingId(), "Canceled");
            AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = appointmentBookingPaymentForUserRepository.getOneAppointmentBookingPaymentForNU(userAppointmentBooking.getBookingId());

            AppointmentBookingPaymentForUser newAppointmentBookingPaymentForUser = new AppointmentBookingPaymentForUser();
            newAppointmentBookingPaymentForUser.setAmount(appointmentBookingPaymentForUser.getAmount());
            newAppointmentBookingPaymentForUser.setAppointmentBookingId(userAppointmentBooking.getBookingId());
            newAppointmentBookingPaymentForUser.setPaymentDate(LocalDateTime.now());
            newAppointmentBookingPaymentForUser.setPaymentDescription("Canceled the appointment booking by the health professional and your booking payment deposit to your bank account");
            newAppointmentBookingPaymentForUser.setPaymentState("Receipts");
            newAppointmentBookingPaymentForUser.setUserId(userAppointmentBooking.getUserId());
            appointmentBookingPaymentForUserRepository.save(newAppointmentBookingPaymentForUser);

            Notification notification = new Notification(
                    userAppointmentBooking.getUserId(),
                    "Cancel the Appointment",
                    "Cancel the Appointment booking Id: " + userAppointmentBooking.getBookingId() + " on " + userAppointmentBooking.getBookingDate() + " by the health professional, Your booking payment credited to Account number : " + userAppointmentBooking.getAccountNumber() + ", Account holder name : " + userAppointmentBooking.getAccountOwnerName() +
                            ", Branch name : " + userAppointmentBooking.getBranchName() + ", Bank name : " + userAppointmentBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }


    }

    public AppointmentRemainingPaymentState calculateRestPaymentWhenDeleteAppointmentScheduleForHp(int appointmentId) {
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(appointmentId);
        LocalDate today = LocalDate.now();
        LocalDate checkLastDate;
        LocalDate AppointmentBookingCloseDate = appointmentSchedule.getAppointmentBookingCloseDate();
        AppointmentScheduleRestPayment appointmentScheduleRestPayment = appointmentScheduleRestPaymentRepository.getAppointmentScheduleRestPaymentForOneAppointmentSchedule(appointmentId);
        LocalDate newPaymentStartDate = appointmentScheduleRestPayment.getNewPaymentStartDate();
        AppointmentRemainingPaymentState appointmentRemainingPaymentState = new AppointmentRemainingPaymentState();
        int AdvancePayment = appointmentScheduleRestPayment.getRemainingAdvancePayment();
        int totalBookingDateCount = 0;
        int totalRoomChargePreDayTillNewPaymentStartDateToYesterday;
        int totalIncome = 0;
        if(AppointmentBookingCloseDate != null && AppointmentBookingCloseDate.isEqual(today)){
            checkLastDate = today.plusDays(1);
        }else {
            checkLastDate = today;
        }

        for (LocalDate date = newPaymentStartDate; date.isBefore(today.plusDays(1)); date = date.plusDays(1)) {
            if (
                    (date.getDayOfWeek() == DayOfWeek.MONDAY && appointmentSchedule.getMonDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.TUESDAY && appointmentSchedule.getTueDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.WEDNESDAY && appointmentSchedule.getWedDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.THURSDAY && appointmentSchedule.getThuDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.FRIDAY && appointmentSchedule.getFriDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.SATURDAY && appointmentSchedule.getSatDay() == 1) ||
                            (date.getDayOfWeek() == DayOfWeek.SUNDAY && appointmentSchedule.getSunDay() == 1)
            ) {
                totalBookingDateCount++;
            }
        }

        totalRoomChargePreDayTillNewPaymentStartDateToYesterday = totalBookingDateCount * appointmentSchedule.getTotalHallCharge();
        List<UserAppointmentBooking> UserAppointmentBookingList = userAppointmentBookingRepository.getBookedParticipantDetailsForAppointmentScheduleForHp(appointmentId, "Booked", "Previous", newPaymentStartDate, checkLastDate);
        for (UserAppointmentBooking userAppointmentBooking : UserAppointmentBookingList) {
            AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = appointmentBookingPaymentForUserRepository.getOneAppointmentBookingPaymentForNU(userAppointmentBooking.getBookingId());
            totalIncome = totalIncome + appointmentBookingPaymentForUser.getAmount();
        }

        int RemainingPayment =  totalIncome - totalRoomChargePreDayTillNewPaymentStartDateToYesterday;
        int AfterDeductingAdvancePayment;

        appointmentRemainingPaymentState.setStartDate(newPaymentStartDate);
        appointmentRemainingPaymentState.setEndDate(today);
        appointmentRemainingPaymentState.setRemainingAdvance(AdvancePayment);
        appointmentRemainingPaymentState.setIncome(totalIncome);
        appointmentRemainingPaymentState.setTotalRoomCharge(totalRoomChargePreDayTillNewPaymentStartDateToYesterday);

        if(RemainingPayment < 0){
            AfterDeductingAdvancePayment = RemainingPayment + AdvancePayment;
            if(AfterDeductingAdvancePayment < 0){
                appointmentRemainingPaymentState.setRemainingPayment(-1 * AfterDeductingAdvancePayment);
                appointmentRemainingPaymentState.setRemainingPaymentState("NeedPayment");
            }else {
                appointmentRemainingPaymentState.setRemainingPayment(AfterDeductingAdvancePayment);
                appointmentRemainingPaymentState.setRemainingPaymentState("OkWithAdvance");
            }

        }else {
            appointmentRemainingPaymentState.setRemainingPayment(RemainingPayment);
            appointmentRemainingPaymentState.setRemainingPaymentState("Ok");
        }

        appointmentRemainingPaymentState.setNewPaymentStartDate(today.plusDays(1));

        return  appointmentRemainingPaymentState;

    }

    public void deleteTheAppointmentScheduleForHp(int appointmentId, int hpId, int remainingPayment, String remainingPaymentState, int remainingAdvancePayment, LocalDate newPaymentStartDate, LocalDate paymentStartDate, LocalDate paymentEndDate) {
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(appointmentId);
        if(Objects.equals(remainingPaymentState, "NeedPayment")){
            appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId,newPaymentStartDate, 0);
            AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
            appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
            appointmentSchedulePayment.setPayment_date(LocalDate.now());
            appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
            appointmentSchedulePayment.setAmount(remainingPayment);
            appointmentSchedulePayment.setHp_id(hpId);
            appointmentSchedulePayment.setPayment_state("Payment");
            appointmentSchedulePayment.setPayment_description("Pay Remaining Payment for " + paymentStartDate + " to " + paymentEndDate + " time period");
            appointmentSchedulePayment.setAppointment_schedule_state("Deleted");
            appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);

                Notification notification = new Notification(
                        hpId,
                        "Delete Appointment Schedule",
                        "AppointmentId : " + appointmentId + " is deleted. You paid Rs." + remainingPayment +
                                "/= payment as a remaining payment for " + paymentStartDate + " to " + paymentEndDate + " time period",
                        "Unread",
                        LocalDateTime.now()
                );
                notificationRepository.save(notification);

        } else {
            if(Objects.equals(remainingPaymentState, "OkWithAdvance")) {
                appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId, newPaymentStartDate, 0);
                if(remainingPayment != 0){
                    AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
                    appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
                    appointmentSchedulePayment.setPayment_date(LocalDate.now());
                    appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
                    appointmentSchedulePayment.setAmount(remainingPayment);
                    appointmentSchedulePayment.setHp_id(hpId);
                    appointmentSchedulePayment.setPayment_state("Receipts");
                    appointmentSchedulePayment.setPayment_description("Deposit Remaining Income Payment of appointment ID: " + appointmentId);
                    appointmentSchedulePayment.setAppointment_schedule_state("Deleted");
                    appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);

                    Notification notification = new Notification(
                            hpId,
                            "Delete Appointment Schedule",
                            "AppointmentId : " + appointmentId + " is deleted. Your remaining income: " + remainingPayment + ". if it is grater that 0, that remaining income credited to Account number : " + appointmentSchedule.getAccountNumber() + ", Account holder name : " + appointmentSchedule.getAccountOwnerName() +
                                    ", Branch name : " + appointmentSchedule.getBranchName() + ", Bank name : " + appointmentSchedule.getBankName() + " bank account",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);

                }else {
                    Notification notification = new Notification(
                            hpId,
                            "Delete Appointment Schedule",
                            "AppointmentId : " + appointmentId + " is deleted.",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);
                }
            }else {
                if((remainingPayment + remainingAdvancePayment) != 0) {
                    AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
                    appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
                    appointmentSchedulePayment.setPayment_date(LocalDate.now());
                    appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
                    appointmentSchedulePayment.setAmount(remainingPayment + remainingAdvancePayment);
                    appointmentSchedulePayment.setHp_id(hpId);
                    appointmentSchedulePayment.setPayment_state("Receipts");
                    appointmentSchedulePayment.setPayment_description("Deposit Remaining Income Payment of appointment ID: " + appointmentId);
                    appointmentSchedulePayment.setAppointment_schedule_state("Deleted");
                    appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);

                    Notification notification = new Notification(
                            hpId,
                            "Delete Appointment Schedule",
                            "AppointmentId : " + appointmentId + " is deleted. Your remaining income: " + remainingPayment + remainingAdvancePayment + ". the remaining income credited to Account number : " + appointmentSchedule.getAccountNumber() + ", Account holder name : " + appointmentSchedule.getAccountOwnerName() +
                                    ", Branch name : " + appointmentSchedule.getBranchName() + ", Bank name : " + appointmentSchedule.getBankName() + " bank account",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);
                }else {
                    Notification notification = new Notification(
                            hpId,
                            "Delete Appointment Schedule",
                            "AppointmentId : " + appointmentId + " is deleted.",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);
                }
            }


        }

        List<UserAppointmentBooking> userAppointmentBookings = userAppointmentBookingRepository.getBookedDetailsWithStartDateAppointmentScheduleForHp(appointmentId, "Booked", LocalDate.now(), "NotParticipate", "Upcoming");

        for (UserAppointmentBooking userAppointmentBooking : userAppointmentBookings) {
            userAppointmentBookingRepository.updateAppointmentStateToCancelForNu(userAppointmentBooking.getBookingId(), "Canceled");
            AppointmentBookingPaymentForUser appointmentBookingPaymentForUser = appointmentBookingPaymentForUserRepository.getOneAppointmentBookingPaymentForNU(userAppointmentBooking.getBookingId());

            AppointmentBookingPaymentForUser newAppointmentBookingPaymentForUser = new AppointmentBookingPaymentForUser();
            newAppointmentBookingPaymentForUser.setAmount(appointmentBookingPaymentForUser.getAmount());
            newAppointmentBookingPaymentForUser.setAppointmentBookingId(userAppointmentBooking.getBookingId());
            newAppointmentBookingPaymentForUser.setPaymentDate(LocalDateTime.now());
            newAppointmentBookingPaymentForUser.setPaymentDescription("Canceled the appointment booking by the health professional and your booking payment deposit to your bank account");
            newAppointmentBookingPaymentForUser.setPaymentState("Receipts");
            newAppointmentBookingPaymentForUser.setUserId(userAppointmentBooking.getUserId());
            appointmentBookingPaymentForUserRepository.save(newAppointmentBookingPaymentForUser);

            Notification notification = new Notification(
                    userAppointmentBooking.getUserId(),
                    "Cancel the Appointment",
                    "Cancel the Appointment booking Id: " + userAppointmentBooking.getBookingId() + " on " + userAppointmentBooking.getBookingDate() + " by the health professional, Your booking payment credited to Account number : " + userAppointmentBooking.getAccountNumber() + ", Account holder name : " + userAppointmentBooking.getAccountOwnerName() +
                            ", Branch name : " + userAppointmentBooking.getBranchName() + ", Bank name : " + userAppointmentBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }

        DeleteAppointmentSchedule deleteAppointmentSchedule = new DeleteAppointmentSchedule(
                appointmentId,
                appointmentSchedule.getRoomId(),
                appointmentSchedule.getTitle(),
                appointmentSchedule.getRoomType(),
                appointmentSchedule.getSunDay(),
                appointmentSchedule.getMonDay(),
                appointmentSchedule.getTueDay(),
                appointmentSchedule.getWedDay(),
                appointmentSchedule.getThuDay(),
                appointmentSchedule.getFriDay(),
                appointmentSchedule.getSatDay(),
                appointmentSchedule.getStartTime(),
                appointmentSchedule.getEndTime(),
                appointmentSchedule.getDuration(),
                appointmentSchedule.getCapacity(),
                appointmentSchedule.getBookingPrice(),
                appointmentSchedule.getTotalHallCharge(),
                appointmentSchedule.getAdvancePercentage(),
                appointmentSchedule.getAdvancePayment(),
                appointmentSchedule.getPaymentId(),
                appointmentSchedule.getHpId(),
                appointmentSchedule.getDailyState(),
                appointmentSchedule.getAccountNumber(),
                appointmentSchedule.getAccountOwnerName(),
                appointmentSchedule.getBranchName(),
                appointmentSchedule.getBankName(),
                appointmentSchedule.getBookingCount(),
                appointmentSchedule.getStartUnavailableDate(),
                appointmentSchedule.getEndUnavailableDate(),
                appointmentSchedule.getAppointmentBookingCloseDate(),
                appointmentSchedule.getDalyUnavailableDate(),
                LocalDateTime.now()
        );
        deleteAppointmentScheduleRepository.save(deleteAppointmentSchedule);
        healthProfessionalAppointmentScheduleRepository.deleteAppointmentScheduleForHp(appointmentId);
    }

    public void closeAppointmentBookingForHp(int appointmentId) {
        LocalDate today = LocalDate.now();
        healthProfessionalAppointmentScheduleRepository.updateAppointmentBookingCloseDateForHp(appointmentId, today);
        userAppointmentBookingRepository.changeTheAppointmentStatePerDayForNu(appointmentId, today, "Previous");
    }

    public void clearAppointmentScheduleUnavailableDaysForHp(int appointmentId) {
        healthProfessionalAppointmentScheduleRepository.clearAppointmentScheduleUnavailableDaysForHp(appointmentId, null);
    }

    public void automaticallyUpdateTheAppointmentDalyState() {
        List<AppointmentSchedule> appointmentSchedulelist = healthProfessionalAppointmentScheduleRepository.getAllAppointmentSchedulesForHp();
        LocalDate today = LocalDate.now();
        for (AppointmentSchedule appointmentSchedule : appointmentSchedulelist) {
            if(
                    (appointmentSchedule.getDalyUnavailableDate() != null && appointmentSchedule.getDalyUnavailableDate().isEqual(today)) ||
                    (appointmentSchedule.getStartUnavailableDate() != null && appointmentSchedule.getEndUnavailableDate() != null &&
                    ((appointmentSchedule.getStartUnavailableDate().isEqual(today)) || (appointmentSchedule.getStartUnavailableDate().isAfter(today))) &&
                    ((appointmentSchedule.getEndUnavailableDate().isEqual(today)) || (appointmentSchedule.getEndUnavailableDate().isBefore(today)))) ||
                    (appointmentSchedule.getStartUnavailableDate() != null && appointmentSchedule.getEndUnavailableDate() == null &&
                    ((appointmentSchedule.getStartUnavailableDate().isEqual(today)) || (appointmentSchedule.getStartUnavailableDate().isAfter(today))))

            ){
                healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleDailyStateForHp(appointmentSchedule.getAppointmentId(), "Unavailable");
            }else {
                healthProfessionalAppointmentScheduleRepository.updateAppointmentScheduleDailyStateForHp(appointmentSchedule.getAppointmentId(), "Available");
            }
        }
    }

    public void getTheAppointmentProfitOrCleanTheRemainingPaymentForHp(int appointmentId, int hpId, int remainingPayment, String remainingPaymentState, LocalDate newPaymentStartDate, LocalDate startDate, LocalDate endDate) {
        AppointmentSchedule appointmentSchedule = healthProfessionalAppointmentScheduleRepository.getAppointmentScheduleMaxBookingCountAndUnavailableDates(appointmentId);
        if(Objects.equals(remainingPaymentState, "NeedPayment")){
            appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId,newPaymentStartDate, 0);
            AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
            appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
            appointmentSchedulePayment.setPayment_date(LocalDate.now());
            appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
            appointmentSchedulePayment.setAmount(remainingPayment);
            appointmentSchedulePayment.setHp_id(hpId);
            appointmentSchedulePayment.setPayment_state("Payment");
            appointmentSchedulePayment.setPayment_description("Pay Remaining Payment for " + startDate + " to " + endDate + " time period");
            appointmentSchedulePayment.setAppointment_schedule_state("Available");
            appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);
            Notification notification = new Notification(
                    hpId,
                    "Pay the remaining payment",
                    "You paid Rs." + remainingPayment +
                            "/= payment as a remaining payment for " + startDate + " to " + endDate + " time period",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        } else {
            if(Objects.equals(remainingPaymentState, "OkWithAdvance")) {
                appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDate(appointmentId, newPaymentStartDate, remainingPayment);

                    Notification notification = new Notification(
                            hpId,
                            "Clear remaining payment",
                            "You clear the remaining payment till " + endDate + ".",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);

            }else {
                appointmentScheduleRestPaymentRepository.updateAppointmentScheduleRestPaymentNewPaymentDateWithoutRemainingAdvance(appointmentId, newPaymentStartDate);
                if((remainingPayment) != 0) {
                    AppointmentSchedulePayment appointmentSchedulePayment = new AppointmentSchedulePayment();
                    appointmentSchedulePayment.setAppointment_schedule_id(appointmentId);
                    appointmentSchedulePayment.setPayment_date(LocalDate.now());
                    appointmentSchedulePayment.setPayment_time(LocalDateTime.now());
                    appointmentSchedulePayment.setAmount(remainingPayment);
                    appointmentSchedulePayment.setHp_id(hpId);
                    appointmentSchedulePayment.setPayment_state("Receipts");
                    appointmentSchedulePayment.setPayment_description("Deposit Remaining Income Payment of appointment ID: " + appointmentId);
                    appointmentSchedulePayment.setAppointment_schedule_state("Available");
                    appointmentSchedulePaymentRepository.save(appointmentSchedulePayment);

                    Notification notification = new Notification(
                            hpId,
                            "Deposit Appointment Income",
                            "AppointmentId : " + appointmentId + " remaining income: " + remainingPayment + " is credited to Account number : " + appointmentSchedule.getAccountNumber() + ", Account holder name : " + appointmentSchedule.getAccountOwnerName() +
                                    ", Branch name : " + appointmentSchedule.getBranchName() + ", Bank name : " + appointmentSchedule.getBankName() + " bank account",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);
                }else {
                    Notification notification = new Notification(
                            hpId,
                            "Clear remaining payment",
                            "You clear the remaining payment till " + endDate + ".",
                            "Unread",
                            LocalDateTime.now()
                    );
                    notificationRepository.save(notification);
                }
            }
        }
    }

}
