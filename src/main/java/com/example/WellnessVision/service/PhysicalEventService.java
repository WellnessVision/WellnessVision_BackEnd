package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.dto.HealthProfessionalFineAmountDto;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalEventService {

    @Autowired
    private PhysicalEventRepository repository;

    @Autowired
    private PhysicalEventBookingRepository physicalEventBookingRepository;
    private final PhysicalEventOrderRepository order_repository;
    private final PhysicalEventPaymentRepository physicalEventPaymentRepository;
    private final DeletedPhysicalEventRepository deletedPhysicalEventRepository;
    private final PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository;
    private final NotificationRepository notificationRepository;

    public PhysicalEventService(PhysicalEventOrderRepository orderRepository, PhysicalEventPaymentRepository physicalEventPaymentRepository, DeletedPhysicalEventRepository deletedPhysicalEventRepository, PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository, NotificationRepository notificationRepository) {
        order_repository = orderRepository;
        this.physicalEventPaymentRepository = physicalEventPaymentRepository;
        this.deletedPhysicalEventRepository = deletedPhysicalEventRepository;
        this.physicalEventBookingPaymentRepository = physicalEventBookingPaymentRepository;
        this.notificationRepository = notificationRepository;
    }

    public HallAvailability getEventsByCapacityAndStatus(int capacity, String hall_type, String Available,String Maintain,String Unavailable, LocalDate date, int start_time, int end_time, String event_title, String event_type, int duration, int ticket_price, String eventImage, String language, String event_description, int hpId, String accountNumber, String ownerName, String branchName, String bankName) throws IOException {
        PhysicalEvent physicalEvent = new PhysicalEvent();
        physicalEvent.setEventTitle(event_title);
        physicalEvent.setFinalEventType(event_type);
        physicalEvent.setDate(date);
        physicalEvent.setStartTime(start_time);
        physicalEvent.setEndTime(end_time);
        physicalEvent.setFinalDuration(duration);
        physicalEvent.setCapacity(capacity);
        physicalEvent.setTicketPrice(ticket_price);
        physicalEvent.setEventImage(eventImage);
        physicalEvent.setLanguage(language);
        physicalEvent.setHp_id(hpId);
        physicalEvent.setEvent_description(event_description);
        physicalEvent.setHall_capacity(0);
        physicalEvent.setTotal_hall_charge(0);
        physicalEvent.setAdvance_percentage(0.00);
        physicalEvent.setAdvance_payment(0);
        physicalEvent.setPayment_id(0);
        physicalEvent.setEvent_state("Upcoming");
        physicalEvent.setAccountNumber(accountNumber);
        physicalEvent.setAccountOwnerName(ownerName);
        physicalEvent.setBranchName(branchName);
        physicalEvent.setBankName(bankName);
        List<Hall> halls = new ArrayList<>();
        halls =  repository.findByCapacityAndStatus(capacity, hall_type, Available, Maintain, Unavailable, date);
        for (Hall hall : halls) {
            int date_booking = repository.findByDate(hall.getHall_id(),date);
            if(date_booking == 0){
                physicalEvent.setHall_id(hall.getHall_id());
                PhysicalEvent savedEvent = order_repository.save(physicalEvent);
                Optional<Hall> bookHall = repository.findById(hall.getHall_id());
                Hall hall_2 = bookHall.get();
                HallAvailability hallAvailability = new HallAvailability();
                hallAvailability.setHall_id(hall_2.getHall_id());
                hallAvailability.setHall_type(hall_2.getHall_type());
                hallAvailability.setCapacity(hall_2.getCapacity());
                hallAvailability.setAdvance_percentage(hall_2.getAdvance_percentage());
                hallAvailability.setCharge(hall_2.getCharge());
                hallAvailability.setEvent_id(savedEvent.getEvent_id());
                return hallAvailability;
            }else {
                int time_booking = repository.findByTime(hall.getHall_id(), date, start_time, end_time);
                if(date_booking == time_booking){
                    physicalEvent.setHall_id(hall.getHall_id());
                    PhysicalEvent savedEvent = order_repository.save(physicalEvent);
                    Optional<Hall> bookHall = repository.findById(hall.getHall_id());
                    Hall hall_2 = bookHall.get();
                    HallAvailability hallAvailability = new HallAvailability();
                    hallAvailability.setHall_id(hall_2.getHall_id());
                    hallAvailability.setHall_type(hall_2.getHall_type());
                    hallAvailability.setCapacity(hall_2.getCapacity());
                    hallAvailability.setAdvance_percentage(hall_2.getAdvance_percentage());
                    hallAvailability.setCharge(hall_2.getCharge());
                    hallAvailability.setEvent_id(savedEvent.getEvent_id());
                    return hallAvailability;
                }
            }
        }

        return null;
    }

    public void cancelHallBooking(int event_id){
        order_repository.deleteById(event_id);
    }

    public void UploadEventImage(String eventImage, int eventId, int hall_capacity, int total_hall_charge, double advance_percentage, int advance_payment, int payment_id) throws IOException {
        repository.UploadEventImage(eventImage, eventId,  hall_capacity, total_hall_charge, advance_percentage, advance_payment, payment_id);
    }

    public Integer physicalEventPaymentSave(PhysicalEventPayment physicalEventPayment) {
        PhysicalEventPayment savedPhysicalEventPayment = physicalEventPaymentRepository.save(physicalEventPayment);
        return savedPhysicalEventPayment.getPayment_id();
    }

    public List<PhysicalEvent> getPhysicalEventForHP(int hp_id, String eventState) {
        return order_repository.getPhysicalEventForHP(hp_id, eventState);
    }

    public PhysicalEvent getOnePhysicalEventDetailForHP(int event_id) {
        return order_repository.getOnePhysicalEventDetailForHP(event_id);
    }

    public HealthProfessionalFineAmountDto getFineAmountForHP(int event_id) {
        PhysicalEvent physicalEvent =  order_repository.getEventDate(event_id);
        LocalDate eventDate =  physicalEvent.getDate();
        LocalDate twoDaysBeforeDate = eventDate.minusDays(2);
        LocalDate today = LocalDate.now();
        int fineAmount;
        String fineAmountDetails;
        int depositAmount = 0;
        if(today.isBefore(twoDaysBeforeDate)){
            fineAmount = (int) (physicalEvent.getTotal_hall_charge() * 0.05);
            depositAmount = physicalEvent.getAdvance_payment() - fineAmount;
            fineAmountDetails = "You are deleting the event two days before the event date. " +
                    "So your fine amount is 5% of the hall's full charge. After deducting the fine " +
                    "from your advance payment, the remaining amount will be credited to your bank account.";
        }else {
            fineAmount = physicalEvent.getAdvance_payment();
            fineAmountDetails = "You are deleting the event with less than two days left for the event. " +
                    "So your fine amount is equal to advance payment.";
        }

        HealthProfessionalFineAmountDto healthProfessionalFineAmountDto = new HealthProfessionalFineAmountDto(
                today,
                eventDate,
                twoDaysBeforeDate,
                fineAmount,
                depositAmount,
                physicalEvent.getTotal_hall_charge(),
                physicalEvent.getAdvance_payment(),
                physicalEvent.getAdvance_percentage(),
                fineAmountDetails
        );

        return healthProfessionalFineAmountDto;

    }

    public void deletePhysicalEventForHP(int event_id, int fineAmount, int depositAmount, String deleteReason) {
        PhysicalEvent physicalEvent =  order_repository.getEventDate(event_id);
        int depositPaymentId = 0;

        if(depositAmount > 0){
        PhysicalEventPayment physicalEventPayment = new PhysicalEventPayment();
        physicalEventPayment.setPhysical_event_id(event_id);
        physicalEventPayment.setPayment_date(LocalDate.now());
        physicalEventPayment.setPayment_time(LocalDateTime.now());
        physicalEventPayment.setAmount(depositAmount);
        physicalEventPayment.setHp_id(physicalEvent.getHp_id());
        physicalEventPayment.setPayment_state("Receipts");
        physicalEventPayment.setPayment_description("Deleting the event two days before the event date");
        physicalEventPayment.setEvent_state("Unavailable");
        depositPaymentId = physicalEventPaymentSave(physicalEventPayment); }

        physicalEventPaymentRepository.updatePaymentState(physicalEvent.getPayment_id(), "Unavailable");

        DeletedPhysicalEvent deletedPhysicalEvent = new DeletedPhysicalEvent(
                physicalEvent.getEvent_id(),
                physicalEvent.getHall_id(),
                physicalEvent.getEventTitle(),
                physicalEvent.getFinalEventType(),
                physicalEvent.getDate(),
                physicalEvent.getStartTime(),
                physicalEvent.getEndTime(),
                physicalEvent.getFinalDuration(),
                physicalEvent.getCapacity(),
                physicalEvent.getTicketPrice(),
                physicalEvent.getEventImage(),
                physicalEvent.getHall_capacity(),
                physicalEvent.getTotal_hall_charge(),
                physicalEvent.getAdvance_percentage(),
                physicalEvent.getAdvance_payment(),
                physicalEvent.getPayment_id(),
                physicalEvent.getLanguage(),
                physicalEvent.getEvent_description(),
                physicalEvent.getHp_id(),
                "Deleted",
                LocalDateTime.now(),
                deleteReason,
                fineAmount,
                depositAmount,
                depositPaymentId,
                physicalEvent.getAccountNumber(),
                physicalEvent.getAccountOwnerName(),
                physicalEvent.getBranchName(),
                physicalEvent.getBankName() );

        deletedPhysicalEventRepository.save(deletedPhysicalEvent);

        order_repository.deleteById(physicalEvent.getEvent_id());

        if(depositAmount > 0){
        Notification notification = new Notification(
                physicalEvent.getHp_id(),
                "Delete Physical Event",
                "You deleted the EventId : " + physicalEvent.getEvent_id() + " event two days before the event date. " +
                        "So your fine amount is 5% of the hall's full charge. Hall's full charge : Rs." + physicalEvent.getTotal_hall_charge() +
                        "/=, Advance payment : Rs." + physicalEvent.getAdvance_payment() + "/=, Fine amount : Rs." + fineAmount +  "/=, deposit amount : Rs." + depositAmount + "/= After deducting the fine " +
                        "from your advance payment, the remaining amount (deposit amount) will be credited to Account number : " + physicalEvent.getAccountNumber() + ", Account holder name : " + physicalEvent.getAccountOwnerName() +
                        ", Branch name : " + physicalEvent.getBranchName() + ", Bank name : " + physicalEvent.getBankName() + " bank account",
                "Unread",
                LocalDateTime.now()
        );
            notificationRepository.save(notification);
        }

        if(depositAmount == 0){
            Notification notification = new Notification(
                    physicalEvent.getHp_id(),
                    "Delete Physical Event",
                    "You deleted the EventId : " + physicalEvent.getEvent_id() + " event with less than two days left for the event. " +
                            "So your fine amount is equal to advance payment. Hall's full charge : Rs." + physicalEvent.getTotal_hall_charge() +
                            "/=, Advance payment : Rs." + physicalEvent.getAdvance_payment() + "/=, Fine amount : Rs." + fineAmount +  "/=",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }

        List<PhysicalEventBooking> physicalEventBookings = physicalEventBookingRepository.getPhysicalEventBooking(event_id, "Booking");
        for (PhysicalEventBooking physicalEventBooking : physicalEventBookings) {
            physicalEventBookingRepository.updateUserPhysicalEventBookingEventState(physicalEventBooking.getBookingId(), "Unavailable");

            PhysicalEventBookingPayment physicalEventBookingPayment = new PhysicalEventBookingPayment();
            physicalEventBookingPayment.setAmount(physicalEvent.getTicketPrice());
            physicalEventBookingPayment.setBookingId(physicalEventBooking.getBookingId());
            physicalEventBookingPayment.setPaymentDate(LocalDateTime.now());
            physicalEventBookingPayment.setPaymentDescription("Canceled the physical event and Receipts your ticket price");
            physicalEventBookingPayment.setPaymentState("Receipts");
            physicalEventBookingPayment.setUserId(physicalEventBooking.getUserId());
            physicalEventBookingPaymentRepository.save(physicalEventBookingPayment);

            Notification notification = new Notification(
                    physicalEventBooking.getUserId(),
                    "Physical Event was deleted",
                    "EventId : " + physicalEvent.getEvent_id() + " was deleted. So your ticket payment : "
                            + physicalEvent.getTicketPrice() + "/= will be credited to Account number : " + physicalEventBooking.getAccountNumber()
                            + ", Account holder name : " + physicalEventBooking.getAccountOwnerName() +
                            ", Branch name : " + physicalEventBooking.getBranchName() + ", Bank name : " + physicalEventBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }

    }

    public void updatePhysicalEventMoneyReceiptsDetailsForHP(int event_id, String account_number, String account_owner_name, String branch_name, String bank_name) {
        repository.updatePhysicalEventMoneyReceiptsDetailsForHP(event_id, account_number, account_owner_name, branch_name, bank_name);
    }

}
