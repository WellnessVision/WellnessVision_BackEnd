package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.*;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.model.PhysicalEvent;
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
    private final NormalUserRegisterRepository normalUserRegisterRepository;

    @Autowired
    VolunteerPhysicalEventRequestRepository volunteerPhysicalEventRequestRepository;

    @Autowired
    VolunteerPhysicalEventBookingRepository volunteerPhysicalEventBookingRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    VolunteerPhysicalEventService volunteerPhysicalEventService;

    public PhysicalEventService(PhysicalEventOrderRepository orderRepository, PhysicalEventPaymentRepository physicalEventPaymentRepository, DeletedPhysicalEventRepository deletedPhysicalEventRepository, PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository, NotificationRepository notificationRepository, NormalUserRegisterRepository normalUserRegisterRepository) {
        this.order_repository = orderRepository;
        this.physicalEventPaymentRepository = physicalEventPaymentRepository;
        this.deletedPhysicalEventRepository = deletedPhysicalEventRepository;
        this.physicalEventBookingPaymentRepository = physicalEventBookingPaymentRepository;
        this.notificationRepository = notificationRepository;
        this.normalUserRegisterRepository = normalUserRegisterRepository;
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

    public void UploadEventImage(String eventImage, int eventId, int hall_capacity, int total_hall_charge, double advance_percentage, int advance_payment, int payment_id, String title, String eventType, String ticketPrice, String language, String description, String accountNumber, String accountHolderName, String branch, String bank) throws IOException {
        repository.UploadEventImage(eventImage, eventId,  hall_capacity, total_hall_charge, advance_percentage, advance_payment, payment_id, title, eventType, ticketPrice, language, description, accountNumber, accountHolderName, branch, bank);
    }

    public Integer physicalEventPaymentSave(PhysicalEventPayment physicalEventPayment) {
        PhysicalEventPayment savedPhysicalEventPayment = physicalEventPaymentRepository.save(physicalEventPayment);
        return savedPhysicalEventPayment.getPayment_id();
    }

    public List<PhysicalEvent> getPhysicalEventForHP(int hp_id, String eventState, String searchCode) {
        String searchCodeModify = searchCode + "%";
        return order_repository.getPhysicalEventForHP(hp_id, eventState, searchCodeModify);
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

        order_repository.deletePhysicalEvent(event_id);

        if(depositAmount > 0){
        Notification notification = new Notification(
                physicalEvent.getHp_id(),
                "Delete Physical Event",
                "You deleted the EventId : " + physicalEvent.getEvent_id() + " event two days before the event date. " + "Reason : " + deleteReason +
                        ". So your fine amount is 5% of the hall's full charge. Hall's full charge : Rs." + physicalEvent.getTotal_hall_charge() +
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
                    "You deleted the EventId : " + physicalEvent.getEvent_id() + " event with less than two days left for the event. " + "Reason : " + deleteReason +
                            ". So your fine amount is equal to advance payment. Hall's full charge : Rs." + physicalEvent.getTotal_hall_charge() +
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
                    "EventId : " + physicalEvent.getEvent_id() + " was deleted. " + "Reason : " + deleteReason + ". So your ticket payment : "
                            + physicalEvent.getTicketPrice() + "/= will be credited to Account number : " + physicalEventBooking.getAccountNumber()
                            + ", Account holder name : " + physicalEventBooking.getAccountOwnerName() +
                            ", Branch name : " + physicalEventBooking.getBranchName() + ", Bank name : " + physicalEventBooking.getBankName() + " bank account",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);
        }

        physicalEventBookingRepository.changeEventStateToForDeletePhysicalEvent(event_id, "Unavailable");

    }

    public void updatePhysicalEventMoneyReceiptsDetailsForHP(int event_id, String account_number, String account_owner_name, String branch_name, String bank_name) {
        repository.updatePhysicalEventMoneyReceiptsDetailsForHP(event_id, account_number, account_owner_name, branch_name, bank_name);
    }

    public List<ViewPhysicalEventParticipationDetails> viewPhysicalEventParticipationDetails(int event_id, String searchCode) {
        String searchCodeModify = searchCode + "%";
        List<PhysicalEventBooking> physicalEventBookings = physicalEventBookingRepository.viewPhysicalEventParticipationDetails(event_id, searchCodeModify);
        List<ViewPhysicalEventParticipationDetails> viewPhysicalEventParticipationDetailsList = new ArrayList<>();
        for (PhysicalEventBooking physicalEventBooking : physicalEventBookings) {
            NormalUser normalUser = normalUserRegisterRepository.getOneUserDetails(physicalEventBooking.getUserId());
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
                    physicalEventBooking.getBookingId(),
                    physicalEventBooking.getBookingState(),
                    physicalEventBooking.getParticipantId(),
                    physicalEventBooking.getParticipantState()
            );
            viewPhysicalEventParticipationDetailsList.add(viewPhysicalEventParticipationDetails);
        }

        return viewPhysicalEventParticipationDetailsList;
    }

    public void updatePhysicalEventParticipationState(int bookingId, String participantState) {
        physicalEventBookingRepository.updatePhysicalEventParticipationState(bookingId, participantState);
    }
    public void closeEventBookingForHp(int eventId) {
        physicalEventBookingRepository.closeEventBookingForHp(eventId, "Previous");
        physicalEventBookingRepository.changeEventStateToForDeletePhysicalEvent(eventId,"Previous");
    }

    public NormalUser bookingParticipationUserDetailsForHp(int userId) {
        return normalUserRegisterRepository.getOneUserDetails(userId);
    }

    public List<Hall> checkTheEventHallUsingDateForPhysicalEvent(String hallType, LocalDate date, String Available, String Maintain, String Unavailable){
        List<Hall> halls =  repository.checkTheEventHallUsingDateForPhysicalEvent(hallType, date, Available, Maintain, Unavailable);
        List<Hall> finalHallList =  new ArrayList<>();
        for (Hall hall : halls) {
            int totalDuration = 0;
            List<PhysicalEvent> physicalEvents = order_repository.getHallBookingAvailableSlotsForGivenHallAndDate(hall.getHall_id(), date);
            for (PhysicalEvent physicalEvent : physicalEvents) {
                totalDuration = totalDuration + physicalEvent.getFinalDuration();
            }
            if(totalDuration != 10){
                finalHallList.add(hall);
            }else {
                hall.setState("fullyBooked");
                finalHallList.add(hall);
            }
        }
        return finalHallList;
    }

    public List<HallBookingTimeSlots> getHallBookingAvailableSlotsForGivenHallAndDate(String hallId, LocalDate date){
        List<PhysicalEvent> physicalEvents = order_repository.getHallBookingAvailableSlotsForGivenHallAndDate(hallId, date);
        List<HallBookingTimeSlots> hallBookingTimeSlots = new ArrayList<>();
        if(physicalEvents.isEmpty()){
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(8);
            hallBookingTimeSlot.setEndTime(18);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
            return hallBookingTimeSlots;
        }
        int FreeStart = physicalEvents.get(0).getStartTime();
        int FinalTime = physicalEvents.get(physicalEvents.size()-1).getEndTime();
        int intermediateEndTime = 8;
        int flag = 1;
        if(FreeStart > 8){
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(8);
            hallBookingTimeSlot.setEndTime(FreeStart);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }
        while (intermediateEndTime != FinalTime) {
            for (PhysicalEvent physicalEvent : physicalEvents) {
                if (physicalEvent.getStartTime() == FreeStart && physicalEvent.getEndTime() > intermediateEndTime) {
                    intermediateEndTime = physicalEvent.getEndTime();
                }
            }
            while (flag == 1) {
                flag = 0;
                for (PhysicalEvent physicalEvent : physicalEvents) {
                    if (physicalEvent.getStartTime() > FreeStart && physicalEvent.getStartTime() <= intermediateEndTime && physicalEvent.getEndTime() > intermediateEndTime) {
                        intermediateEndTime = physicalEvent.getEndTime();
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    for (PhysicalEvent physicalEvent : physicalEvents) {
                        if (intermediateEndTime < physicalEvent.getStartTime()) {
                            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
                            hallBookingTimeSlot.setStartTime(intermediateEndTime);
                            hallBookingTimeSlot.setEndTime(physicalEvent.getStartTime());
                            hallBookingTimeSlots.add(hallBookingTimeSlot);
                            FreeStart = physicalEvent.getStartTime();
                            intermediateEndTime = physicalEvent.getStartTime();
                            break;
                        }
                    }
                }
            }
        }

        if(FinalTime < 18){
            HallBookingTimeSlots hallBookingTimeSlot = new HallBookingTimeSlots();
            hallBookingTimeSlot.setStartTime(FinalTime);
            hallBookingTimeSlot.setEndTime(18);
            hallBookingTimeSlots.add(hallBookingTimeSlot);
        }

        return hallBookingTimeSlots;
    }

    public HallAvailability checkAndTemporarilyBookingEventHall(int hpId, String hallId, LocalDate date, int startTime, int endTime, int duration){
        PhysicalEvent physicalEvent = new PhysicalEvent();
        physicalEvent.setEventTitle("");
        physicalEvent.setFinalEventType("");
        physicalEvent.setDate(date);
        physicalEvent.setStartTime(startTime);
        physicalEvent.setEndTime(endTime);
        physicalEvent.setFinalDuration(duration);
        physicalEvent.setCapacity(0);
        physicalEvent.setTicketPrice(0);
        physicalEvent.setEventImage("pending");
        physicalEvent.setLanguage("");
        physicalEvent.setHp_id(hpId);
        physicalEvent.setEvent_description("");
        physicalEvent.setHall_capacity(0);
        physicalEvent.setTotal_hall_charge(0);
        physicalEvent.setAdvance_percentage(0.00);
        physicalEvent.setAdvance_payment(0);
        physicalEvent.setPayment_id(0);
        physicalEvent.setEvent_state("Upcoming");
        physicalEvent.setAccountNumber("");
        physicalEvent.setAccountOwnerName("");
        physicalEvent.setBranchName("");
        physicalEvent.setBankName("");
        int date_booking = repository.findByDate(hallId,date);
        if(date_booking == 0){
            physicalEvent.setHall_id(hallId);
            PhysicalEvent savedEvent = order_repository.save(physicalEvent);
            Optional<Hall> bookHall = repository.findById(hallId);
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
            int time_booking = repository.findByTime(hallId, date, startTime, endTime);
            if(date_booking == time_booking){
                physicalEvent.setHall_id(hallId);
                PhysicalEvent savedEvent = order_repository.save(physicalEvent);
                Optional<Hall> bookHall = repository.findById(hallId);
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

        return null;
    }

    public void addNewVolunteerNeedRequestForHP(int eventId, String volunteerType, String volunteerDescription){
        order_repository.addNewVolunteerNeedRequestForHP(eventId, volunteerType, volunteerDescription, "Need");
    }

    public void acceptVolunteerPhysicalEventRequestForHP(int requestId){
        VolunteerPhysicalEventRequest newVolunteerPhysicalEventRequest = volunteerPhysicalEventRequestRepository.getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(requestId);
        VolunteerPhysicalEventBooking newVolunteerPhysicalEventBooking = new VolunteerPhysicalEventBooking(
                newVolunteerPhysicalEventRequest.getEventId(),
                newVolunteerPhysicalEventRequest.getVolunteerId(),
                "Booked",
                "Available",
                "V/" + RandomStringGeneratorService.generateRandomString(7),
                "NotParticipate",
                newVolunteerPhysicalEventRequest.getRequestPosition(),
                newVolunteerPhysicalEventRequest.getExperiences(),
                newVolunteerPhysicalEventRequest.getPreviousWorksPdf(),
                newVolunteerPhysicalEventRequest.getRequestTime(),
                LocalDateTime.now()
        );

        volunteerPhysicalEventBookingRepository.save(newVolunteerPhysicalEventBooking);

        volunteerPhysicalEventRequestRepository.deleteTheVolunteerRequestForPhysicalEventsForHPByRequestId(requestId);

        Notification notification = new Notification(
                newVolunteerPhysicalEventRequest.getVolunteerId(),
                "Accepted Volunteer request",
                "Your volunteer request of EventId : " + newVolunteerPhysicalEventRequest.getEventId() + " was accepted. You can view more " +
                        "details about this under My Volunteer Event category",
                "Unread",
                LocalDateTime.now()
        );
        notificationRepository.save(notification);

    }

    public List<VolunteerDetailsForPhysicalEventDto> getAllVolunteersForPhysicalEventsForHealthProfessionals(int eventId, String searchCode){
        String modifiedSearchCode = searchCode + "%";
        List<VolunteerDetailsForPhysicalEventDto> volunteerDetailsForPhysicalEventDtoList = new ArrayList<>();
        List<VolunteerPhysicalEventBooking> volunteerPhysicalEventBookingList  = volunteerPhysicalEventBookingRepository.getAllVolunteersForPhysicalEventsForHealthProfessionals(eventId, modifiedSearchCode, "Booked");
        for (VolunteerPhysicalEventBooking oneVolunteerPhysicalEventBooking: volunteerPhysicalEventBookingList) {
            Volunteer volunteer = volunteerRepository.getVolunteerDetailsByVolunteerId(oneVolunteerPhysicalEventBooking.getVolunteerId());
            int unreadMessageCount = volunteerPhysicalEventService.getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(volunteer.getVolunteerId(), eventId, "V");
            VolunteerDetailsForPhysicalEventDto newVolunteerDetailsForPhysicalEventDto = new VolunteerDetailsForPhysicalEventDto(
                    volunteer.getVolunteerId(),
                    oneVolunteerPhysicalEventBooking.getBookingId(),
                    eventId,
                    oneVolunteerPhysicalEventBooking.getParticipantId(),
                    oneVolunteerPhysicalEventBooking.getBookingState(),
                    oneVolunteerPhysicalEventBooking.getParticipantState(),
                    volunteer.getFirstName(),
                    volunteer.getLastName(),
                    volunteer.getAddress(),
                    volunteer.getAddress2(),
                    volunteer.getCity(),
                    volunteer.getDistrict(),
                    volunteer.getProvince(),
                    volunteer.getZip(),
                    volunteer.getEmail(),
                    volunteer.getPhoneNumber(),
                    volunteer.getProfilePicture(),
                    oneVolunteerPhysicalEventBooking.getRequestPosition(),
                    oneVolunteerPhysicalEventBooking.getExperiences(),
                    oneVolunteerPhysicalEventBooking.getPreviousWorksPdf(),
                    oneVolunteerPhysicalEventBooking.getRequestTime(),
                    oneVolunteerPhysicalEventBooking.getAcceptTime(),
                    unreadMessageCount
            );
            volunteerDetailsForPhysicalEventDtoList.add(newVolunteerDetailsForPhysicalEventDto);
        }

        return volunteerDetailsForPhysicalEventDtoList;
    }

    public VolunteerDetailsForPhysicalEventDto getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals(int bookingId){
        VolunteerPhysicalEventBooking volunteerPhysicalEventBooking  = volunteerPhysicalEventBookingRepository.getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals(bookingId);
        Volunteer volunteer = volunteerRepository.getVolunteerDetailsByVolunteerId(volunteerPhysicalEventBooking.getVolunteerId());
        int unreadMessageCount = volunteerPhysicalEventService.getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(volunteer.getVolunteerId(), volunteerPhysicalEventBooking.getEventId(), "V");
        VolunteerDetailsForPhysicalEventDto newVolunteerDetailsForPhysicalEventDto = new VolunteerDetailsForPhysicalEventDto(
                    volunteer.getVolunteerId(),
                    volunteerPhysicalEventBooking.getBookingId(),
                    volunteerPhysicalEventBooking.getEventId(),
                    volunteerPhysicalEventBooking.getParticipantId(),
                    volunteerPhysicalEventBooking.getBookingState(),
                    volunteerPhysicalEventBooking.getParticipantState(),
                    volunteer.getFirstName(),
                    volunteer.getLastName(),
                    volunteer.getAddress(),
                    volunteer.getAddress2(),
                    volunteer.getCity(),
                    volunteer.getDistrict(),
                    volunteer.getProvince(),
                    volunteer.getZip(),
                    volunteer.getEmail(),
                    volunteer.getPhoneNumber(),
                    volunteer.getProfilePicture(),
                    volunteerPhysicalEventBooking.getRequestPosition(),
                    volunteerPhysicalEventBooking.getExperiences(),
                    volunteerPhysicalEventBooking.getPreviousWorksPdf(),
                    volunteerPhysicalEventBooking.getRequestTime(),
                    volunteerPhysicalEventBooking.getAcceptTime(),
                    unreadMessageCount
            );

        return newVolunteerDetailsForPhysicalEventDto;
    }

}
