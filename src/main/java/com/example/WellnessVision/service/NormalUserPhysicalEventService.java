package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.HealthProfessionalFineAmountDto;
import com.example.WellnessVision.dto.NormalUserFineAmountDto;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
public class NormalUserPhysicalEventService {

    @Autowired
    private NormalUserGetPhysicalEventsRepository normalUserGetPhysicalEventsRepository;
    private final PhysicalEventBookingRepository physicalEventBookingRepository;
    private final PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository;
    private final NotificationRepository notificationRepository;
    private final PhysicalEventOrderRepository order_repository;

    public NormalUserPhysicalEventService(PhysicalEventBookingRepository physicalEventBookingRepository, PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository, NotificationRepository notificationRepository, PhysicalEventOrderRepository orderRepository) {
        this.physicalEventBookingRepository = physicalEventBookingRepository;
        this.physicalEventBookingPaymentRepository = physicalEventBookingPaymentRepository;
        this.notificationRepository = notificationRepository;
        order_repository = orderRepository;
    }

    public List<PhysicalEvent> getUpcomingPhysicalEventsForUsers(String eventState){
        return normalUserGetPhysicalEventsRepository.getUpcomingPhysicalEventsForUsers(eventState);
    }

    public int checkBookingStateOfOnePhysicalEventDetailForUser(int event_id, int user_id){
        return normalUserGetPhysicalEventsRepository.checkBookingStateOfOnePhysicalEventDetailForUser(event_id, user_id);
    }

//    public List<PhysicalEvent> getAllBookedPhysicalEventsByUserId(int user_id){
//        return normalUserGetPhysicalEventsRepository.getBookedPhysicalEventsForUserByUserId(user_id);
//    }

    public void temporaryBookingPhysicalEventTicket(int eventID){
        normalUserGetPhysicalEventsRepository.temporaryBookingPhysicalEventTicket(eventID);
    }

    public void userPhysicalEventTemporaryBookingCancel(int eventID){
        normalUserGetPhysicalEventsRepository.userPhysicalEventTemporaryBookingCancel(eventID);
    }

    public void userPhysicalEventBooking(int event_id, int user_id, int ticketPrice, String account_number, String account_owner_name, String branch_name, String bank_name){

        String participantId = "7845812";

        PhysicalEventBooking physicalEventBooking = new PhysicalEventBooking(
                event_id,
                user_id,
                "Booking",
                "Available",
                participantId,
                "NotParticipate",
                account_number,
                account_owner_name,
                branch_name,
                bank_name
        );

        physicalEventBookingRepository.save(physicalEventBooking);

        PhysicalEventBookingPayment physicalEventBookingPayment = new PhysicalEventBookingPayment(
                physicalEventBooking.getBookingId(),
                user_id,
                LocalDateTime.now(),
                ticketPrice,
                "Payment",
                "Booking physical event"
        );

        physicalEventBookingPaymentRepository.save(physicalEventBookingPayment);

        Notification notification = new Notification(
                user_id,
                "Physical Event Booking",
                "EventId : " + event_id + " event ticket was booked for you. Your participant ID : " + participantId +
                        ". You can view more details under booked upcoming physical event category",
                "Unread",
                LocalDateTime.now()
        );
        notificationRepository.save(notification);
    }

    public List<PhysicalEvent> getBookedUpcomingPhysicalEventsForUsers(int userId, String bookingState, String eventState){
        return normalUserGetPhysicalEventsRepository.getBookedUpcomingPhysicalEventsForUsers(userId, bookingState, eventState);
    }

    public PhysicalEventBooking getBookingDetailsBookedUpcomingPhysicalEventsForUsers(int userId, int eventId, String bookingState){
        return physicalEventBookingRepository.getBookingDetailsBookedUpcomingPhysicalEventsForUsers(userId, eventId, bookingState);
    }

    public void updateOnePhysicalEventMoneyReceiptsDetailsForNU(int booking_id, String account_number, String account_owner_name, String branch_name, String bank_name){
         physicalEventBookingRepository.updateOnePhysicalEventMoneyReceiptsDetailsForNU(booking_id, account_number, account_owner_name, branch_name, bank_name);
    }

    public NormalUserFineAmountDto getFineAmountForNU(int event_id) {
        PhysicalEvent physicalEvent =  order_repository.getEventDate(event_id);
        LocalDate eventDate =  physicalEvent.getDate();
        LocalDate twoDaysBeforeDate = eventDate.minusDays(2);
        LocalDate today = LocalDate.now();
        int ticketPrice = physicalEvent.getTicketPrice();
        int fineAmount;
        double finePercentage;
        String fineAmountDetails;
        int depositAmount = 0;
        String twoDaysBeforeState;
        if(today.isBefore(twoDaysBeforeDate)){
            twoDaysBeforeState = "Yes";
            finePercentage = 15.00;
            fineAmount = (int) (ticketPrice * 0.15);
            depositAmount = ticketPrice - fineAmount;
            fineAmountDetails = "You are deleting the event booking two days before the event date. " +
                    "So your fine amount is 15% of the full ticket price. After deducting the fine " +
                    "from your booking payment, the remaining amount will be credited to your bank account.";
        }else {
            twoDaysBeforeState = "No";
            finePercentage = 50.00;
            fineAmount = (int) (ticketPrice * 0.50);
            depositAmount = ticketPrice - fineAmount;
            fineAmountDetails = "You are deleting the event booking with less than two days left for the event. " +
                    "So your fine amount is 50% of the full ticket price. After deducting the fine " +
                    "from your booking payment, the remaining amount will be credited to your bank account.";
        }

        NormalUserFineAmountDto normalUserFineAmountDto = new NormalUserFineAmountDto(
                today,
                eventDate,
                twoDaysBeforeDate,
                ticketPrice,
                fineAmount,
                depositAmount,
                finePercentage,
                fineAmountDetails,
                twoDaysBeforeState
        );

        return normalUserFineAmountDto;

    }

    public void deletePhysicalEventBookingForNU(int event_id, int user_id, int booking_id, int fineAmount, int depositAmount, String twoDaysBeforeState){

        normalUserGetPhysicalEventsRepository.userPhysicalEventTemporaryBookingCancel(event_id);
        physicalEventBookingRepository.updateUserPhysicalEventBookingState(booking_id, "Canceled");
        PhysicalEventBooking physicalEventBooking = physicalEventBookingRepository.PhysicalEventsBookingDetailsForUsers(booking_id);

        PhysicalEventBookingPayment physicalEventBookingPayment = new PhysicalEventBookingPayment();
        physicalEventBookingPayment.setAmount(fineAmount);
        physicalEventBookingPayment.setBookingId(booking_id);
        physicalEventBookingPayment.setPaymentDate(LocalDateTime.now());
        physicalEventBookingPayment.setPaymentDescription("Canceled the physical event booking and Receipts your deposit amount after deducting the fine from your booking payment");
        physicalEventBookingPayment.setPaymentState("Receipts");
        physicalEventBookingPayment.setUserId(user_id);
        physicalEventBookingPaymentRepository.save(physicalEventBookingPayment);

        Notification notification;
        if(Objects.equals(twoDaysBeforeState, "Yes")){
            notification = new Notification(
                    user_id,
                    "Delete Physical Event Booking",
                    "You deleted the EventId : " + event_id + " event booking two days before the event date. " +
                            "So your fine amount is 15% of the booking payment. Booking payment : Rs." + (fineAmount + depositAmount) +
                            "/=, Fine amount : Rs." + fineAmount + "/=, deposit amount : Rs." + depositAmount + "/= After deducting the fine " +
                            "from your booking payment, the remaining amount (deposit amount) will be credited to Account number : " + physicalEventBooking.getAccountNumber() + ", Account holder name : " + physicalEventBooking.getAccountOwnerName() +
                            ", Branch name : " + physicalEventBooking.getBranchName() + ", Bank name : " + physicalEventBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
        }else {
            notification = new Notification(
                    user_id,
                    "Delete Physical Event Booking",
                    "You deleted the EventId : " + event_id + " event booking with less than two days left for the event. " +
                            "So your fine amount is 50% of the booking payment. Booking payment : Rs." + (fineAmount + depositAmount) +
                            "/=, Fine amount : Rs." + fineAmount + "/=, deposit amount : Rs." + depositAmount + "/= After deducting the fine " +
                            "from your booking payment, the remaining amount (deposit amount) will be credited to Account number : " + physicalEventBooking.getAccountNumber() + ", Account holder name : " + physicalEventBooking.getAccountOwnerName() +
                            ", Branch name : " + physicalEventBooking.getBranchName() + ", Bank name : " + physicalEventBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
        }
        notificationRepository.save(notification);

    }

}
