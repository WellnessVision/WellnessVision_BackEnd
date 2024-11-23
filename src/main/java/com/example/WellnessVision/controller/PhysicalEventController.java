package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.*;
import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.VolunteerPhysicalEventBooking;
import com.example.WellnessVision.repository.VolunteerPhysicalEventBookingRepository;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")

public class PhysicalEventController {

    @Autowired
    private PhysicalEventService service;

    @PostMapping("/physicalEvent")
    public HallAvailability getEventsByCapacityAndStatus(@RequestBody PhysicalEvent physicalEvent) throws IOException {
        String Available = "Available";
        String Maintain = "Maintain";
        String Unavailable = "Unavailable";
        return service.getEventsByCapacityAndStatus(physicalEvent.getExpectedCapacity(),physicalEvent.getHallType(), Available, Maintain, Unavailable, physicalEvent.getDate(), physicalEvent.getStartTime(), physicalEvent.getEndTime(), physicalEvent.getEventTitle(), physicalEvent.getFinalEventType(), physicalEvent.getFinalDuration(), physicalEvent.getTicketPrice(), physicalEvent.getNullImage(), physicalEvent.getLanguage(), physicalEvent.getEventDescription(), physicalEvent.getHpId(), physicalEvent.getAccountNumber(), physicalEvent.getAccountOwnerName(), physicalEvent.getBranchName(), physicalEvent.getBankName());
    }

    @GetMapping("/checkAndTemporarilyBookingEventHall")
    public HallAvailability checkAndTemporarilyBookingEventHall(@RequestParam("hpId") int hpId, @RequestParam("hallId") String hallId, @RequestParam("date") LocalDate date,  @RequestParam("startTime") int startTime,  @RequestParam("endTime") int endTime, @RequestParam("duration") int duration) throws IOException {
        return service.checkAndTemporarilyBookingEventHall(hpId, hallId, date, startTime, endTime, duration);
    }

    @GetMapping("/checkTheEventHallUsingDateForPhysicalEvent")
    public List<Hall> checkTheEventHallUsingDateForPhysicalEvent(@RequestParam("hallType") String hallType, @RequestParam("date") LocalDate date) throws IOException {
        String Available = "Available";
        String Maintain = "Maintain";
        String Unavailable = "Unavailable";
        return service.checkTheEventHallUsingDateForPhysicalEvent(hallType, date, Available, Maintain, Unavailable);
    }

    @GetMapping("/getHallBookingAvailableSlotsForGivenHallAndDate")
    public List<HallBookingTimeSlots> getHallBookingAvailableSlotsForGivenHallAndDate(@RequestParam("hallId") String hallId, @RequestParam("date") LocalDate date) throws IOException {
        return service.getHallBookingAvailableSlotsForGivenHallAndDate(hallId, date);
    }

    @PutMapping("/physicalEvent")
    public void cancelHallBooking(@RequestBody EventID eventID) {
        service.cancelHallBooking(eventID.getEvent_id());
    }

    @GetMapping("/viewPhysicalEvent")
    public List<com.example.WellnessVision.model.PhysicalEvent> getPhysicalEventForHP(@RequestParam("hp_id") int hp_id, @RequestParam("eventState") String eventState, @RequestParam("searchCode") String searchCode) {
        return (List<com.example.WellnessVision.model.PhysicalEvent>) service.getPhysicalEventForHP(hp_id, eventState, searchCode);
    }

    @GetMapping("/viewOnePhysicalEventDetail")
    public com.example.WellnessVision.model.PhysicalEvent getOnePhysicalEventDetailForHP(@RequestParam("eventId") int event_id) {
        return (com.example.WellnessVision.model.PhysicalEvent) service.getOnePhysicalEventDetailForHP(event_id);
    }

    @GetMapping("/viewFineAmountForHP")
    public HealthProfessionalFineAmountDto getFineAmountForHP(@RequestParam("eventId") int event_id) {
        return service.getFineAmountForHP(event_id);
    }

    @PutMapping("/deletePhysicalEventForHP")
    public void deletePhysicalEventForHP(@RequestParam("eventId") int event_id, @RequestParam("fineAmount") int fineAmount, @RequestParam("depositAmount") int depositAmount, @RequestParam("deleteReason") String deleteReason) {
        service.deletePhysicalEventForHP(event_id, fineAmount, depositAmount, deleteReason);
    }

    @PutMapping("/updatePhysicalEventMoneyReceiptsDetailsForHP")
    public void updatePhysicalEventMoneyReceiptsDetailsForHP(@RequestParam("eventId") int event_id, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        service.updatePhysicalEventMoneyReceiptsDetailsForHP(event_id, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/viewPhysicalEventParticipationDetails")
    public List<ViewPhysicalEventParticipationDetails> viewPhysicalEventParticipationDetails(@RequestParam("eventId") int event_id, @RequestParam("searchCode") String searchCode) {
        return service.viewPhysicalEventParticipationDetails(event_id, searchCode);
    }

    @PutMapping("/updatePhysicalEventParticipationState")
    public void updatePhysicalEventParticipationState(@RequestParam("bookingId") int bookingId, @RequestParam("participantState") String participantState)
    {
        service.updatePhysicalEventParticipationState(bookingId, participantState);
    }

    @PutMapping("/closeEventBookingForHp")
    public void closeEventBookingForHp(@RequestParam("eventId") int eventId)
    {
        service.closeEventBookingForHp(eventId);
    }

    @GetMapping("/bookingParticipationUserDetailsForHp")
    public NormalUser bookingParticipationUserDetailsForHp(@RequestParam("userId") int userId) {
        return service.bookingParticipationUserDetailsForHp(userId);
    }
    @PutMapping("/addNewVolunteerNeedRequestForHP")
    public void addNewVolunteerNeedRequestForHP(@RequestParam("eventId") int eventId, @RequestParam("volunteerType") String volunteerType, @RequestParam("volunteerDescription") String volunteerDescription) {
        service.addNewVolunteerNeedRequestForHP(eventId, volunteerType, volunteerDescription);
    }

    @PutMapping("/acceptVolunteerPhysicalEventRequestForHP")
    public void acceptVolunteerPhysicalEventRequestForHP(@RequestParam("requestId") int requestId) {
        service.acceptVolunteerPhysicalEventRequestForHP(requestId);
    }

    @GetMapping("/getAllVolunteersForPhysicalEventsForHealthProfessionals")
    public List<VolunteerDetailsForPhysicalEventDto> getAllVolunteersForPhysicalEventsForHealthProfessionals(@RequestParam("eventId") int eventId, @RequestParam("searchCode") String searchCode) {
        return service.getAllVolunteersForPhysicalEventsForHealthProfessionals(eventId, searchCode);
    }

    @GetMapping("/getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals")
    public VolunteerDetailsForPhysicalEventDto getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals(@RequestParam("bookingId") int bookingId) {
        return service.getOneVolunteerDetailsForPhysicalEventsForHealthProfessionals(bookingId);
    }

    @PutMapping("/autoUpdateThePhysicalEventStateToPrevious")
    public void autoUpdateThePhysicalEventStateToPrevious()
    {
        service.autoUpdateThePhysicalEventStateToPrevious();
    }

}
