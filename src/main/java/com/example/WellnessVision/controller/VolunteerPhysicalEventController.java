package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.NormalUserFineAmountDto;
import com.example.WellnessVision.dto.VolunteerRequestForPhysicalEventsDto;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.NormalUserPhysicalEventService;
import com.example.WellnessVision.service.NormalUserRegisterService;
import com.example.WellnessVision.service.PhysicalEventService;
import com.example.WellnessVision.service.VolunteerPhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:5173")

public class VolunteerPhysicalEventController {

    @Autowired
    private VolunteerPhysicalEventService volunteerPhysicalEventService;


    @GetMapping("/getVolunteerDetails")
    public Volunteer getVolunteerDetails(@RequestParam("volunteerId") int volunteerId) {

        return volunteerPhysicalEventService.getVolunteerDetails(volunteerId);
    }

    @GetMapping("/getVolunteerUpcomingPhysicalEventsForVolunteers")
    public List<PhysicalEvent> getVolunteerUpcomingPhysicalEventsForVolunteers(@RequestParam("volunteerId") int volunteerId,  @RequestParam("bookingState") String bookingState, @RequestParam("eventState") String eventState) {
        return volunteerPhysicalEventService.getVolunteerUpcomingPhysicalEventsForVolunteers(volunteerId, bookingState, eventState);
    }

    @GetMapping("/getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer")
    public VolunteerPhysicalEventBooking getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer(@RequestParam("VolunteerId") int VolunteerId, @RequestParam("eventId") int eventId) {
        return volunteerPhysicalEventService.getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer(VolunteerId, eventId, "Booked");
    }

    @GetMapping("/getChatBoxForVolunteerAndPhysicalEventsForVolunteer")
    public List<ChatBoxVolunteerHealthProfessional> getChatBoxForVolunteerAndPhysicalEventsForVolunteer(@RequestParam("VolunteerId") int VolunteerId, @RequestParam("eventId") int eventId) {
        return volunteerPhysicalEventService.getChatBoxForVolunteerAndPhysicalEventsForVolunteer(VolunteerId, eventId);
    }

    @GetMapping("/getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer")
    public List<ChatBoxVolunteerHealthProfessional> getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(@RequestParam("replyMessageId") int replyMessageId) {
        return volunteerPhysicalEventService.getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(replyMessageId);
    }

    @PostMapping("/newVolunteerMessageToHpPhysicalEventsForVolunteer")
    public void newVolunteerMessageToHpPhysicalEventsForVolunteer(@RequestParam("VolunteerId") int VolunteerId, @RequestParam("userEmail") String userEmail, @RequestParam("eventId") int eventId, @RequestParam("sender") String sender, @RequestParam("messageFlag")  String messageFlag, @RequestParam("message") String message, @RequestParam("attachmentFlag") String attachmentFlag, @RequestParam("attachment") MultipartFile attachment, @RequestParam("replyFlag") String replyFlag, @RequestParam("replyMessage") String replyMessage, @RequestParam("replyMessageAttachmentFlag") String replyMessageAttachmentFlag)
    {
        volunteerPhysicalEventService.newVolunteerMessageToHpPhysicalEventsForVolunteer(VolunteerId, userEmail, eventId, sender, messageFlag, message, attachmentFlag, attachment, replyFlag, replyMessage,replyMessageAttachmentFlag);
    }

    @PutMapping ("/updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer")
    public void updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(@RequestParam("replyMessageId") int replyMessageId, @RequestParam("newMessage") String newMessage) {
        volunteerPhysicalEventService.updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(replyMessageId, newMessage);
    }

    @PutMapping ("/deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer")
    public void deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(@RequestParam("deleteMessageId") int deleteMessageId) {
        volunteerPhysicalEventService.deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(deleteMessageId);
    }

    @PutMapping ("/seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers")
    public void seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(@RequestParam("VolunteerId") int VolunteerId, @RequestParam("eventId") int eventId, @RequestParam("sender") String sender) {
        volunteerPhysicalEventService.seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(VolunteerId, eventId, sender);
    }

    @GetMapping("/getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers")
    public Integer getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(@RequestParam("VolunteerId") int VolunteerId, @RequestParam("eventId") int eventId, @RequestParam("receiver") String receiver) {
        return volunteerPhysicalEventService.getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(VolunteerId, eventId, receiver);
    }

    @GetMapping("/getVolunteerNeedUpcomingPhysicalEventsForVolunteer")
    public List<PhysicalEvent> getVolunteerNeedUpcomingPhysicalEventsForVolunteer(@RequestParam("eventState") String eventState, @RequestParam("searchTitleCode") String searchTitleCode,  @RequestParam("searchVolunteerCode") String searchVolunteerCode) {
        return volunteerPhysicalEventService.getVolunteerNeedUpcomingPhysicalEventsForVolunteer(eventState, searchTitleCode, searchVolunteerCode);
    }

    @GetMapping("/checkVolunteerRequestForPhysicalEventsForVolunteer")
    public String checkVolunteerRequestForPhysicalEventsForVolunteer(@RequestParam("volunteerId") int volunteerId, @RequestParam("eventId") int eventId) {
        return volunteerPhysicalEventService.checkVolunteerRequestForPhysicalEventsForVolunteer(volunteerId, eventId);
    }

    @PostMapping("/createVolunteerRequestForPhysicalEventsForVolunteer")
    public void createVolunteerRequestForPhysicalEventsForVolunteer(@RequestParam("volunteerId") int volunteerId, @RequestParam("eventId") int eventId, @RequestParam("hpId") int hpId, @RequestParam("requestPosition") String requestPosition, @RequestParam("experiences") String experiences, @RequestParam("previousWorksPdf") MultipartFile previousWorksPdf, @RequestParam("userEmail") String userEmail) {
        volunteerPhysicalEventService.createVolunteerRequestForPhysicalEventsForVolunteer(volunteerId, eventId, hpId, requestPosition, experiences, previousWorksPdf, userEmail);
    }

    @PutMapping ("/deleteTheVolunteerRequestForPhysicalEventsForVolunteer")
    public void deleteTheVolunteerRequestForPhysicalEventsForVolunteer(@RequestParam("volunteerId") int volunteerId, @RequestParam("eventId") int eventId, @RequestParam("hpId") int hpId) {
        volunteerPhysicalEventService.deleteTheVolunteerRequestForPhysicalEventsForVolunteer(volunteerId, eventId, hpId);
    }

    @GetMapping("/getVolunteerRequestForPhysicalEventsForHealthProfessionals")
    public List<VolunteerRequestForPhysicalEventsDto> getVolunteerRequestForPhysicalEventsForHealthProfessionals(@RequestParam("eventId") int eventId) {
        return volunteerPhysicalEventService.getVolunteerRequestForPhysicalEventsForHealthProfessionals(eventId);
    }

    @GetMapping("/getOneVolunteerRequestForPhysicalEventsForHealthProfessionals")
    public VolunteerRequestForPhysicalEventsDto getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(@RequestParam("requestId") int requestId) {
        return volunteerPhysicalEventService.getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(requestId);
    }

    @GetMapping("/getOneVolunteerWorkCollectionForVolunteer")
    public List<VolunteerWorkCollection> getOneVolunteerWorkCollectionForVolunteer(@RequestParam("volunteerId") int volunteerId) {
        return volunteerPhysicalEventService.getOneVolunteerWorkCollectionForVolunteer(volunteerId);
    }

    @PostMapping("/addNewWorkToWorkCollectionForVolunteer")
    public void addNewWorkToWorkCollectionForVolunteer(@RequestParam("volunteerId") int volunteerId,  @RequestParam("subject") String subject,  @RequestParam("description") String description, @RequestParam("attachment") MultipartFile attachment, @RequestParam("userEmail") String userEmail) {
        volunteerPhysicalEventService.addNewWorkToWorkCollectionForVolunteer(volunteerId, subject, description, attachment, userEmail);
    }

    @PutMapping ("/editTextInWorkCollectionForVolunteer")
    public void editTextInWorkCollectionForVolunteer(@RequestParam("workCollectionId") int workCollectionId, @RequestParam("subject") String subject, @RequestParam("description") String description) {
        volunteerPhysicalEventService.editTextInWorkCollectionForVolunteer(workCollectionId, subject, description);
    }

    @PostMapping("/editAttachmentInWorkCollectionForVolunteer")
    public void editAttachmentInWorkCollectionForVolunteer(@RequestParam("workCollectionId") int workCollectionId, @RequestParam("attachment") MultipartFile attachment, @RequestParam("userEmail") String userEmail) {
        volunteerPhysicalEventService.editAttachmentInWorkCollectionForVolunteer(workCollectionId, attachment, userEmail);
    }

    @PutMapping ("/deleteWorkInWorkCollectionForVolunteer")
    public void deleteWorkInWorkCollectionForVolunteer(@RequestParam("deletedWorkCollectionId") int deletedWorkCollectionId) {
        volunteerPhysicalEventService.deleteWorkInWorkCollectionForVolunteer(deletedWorkCollectionId);
    }

    @PutMapping ("/updateTheVolunteerParticipateStateForVolunteer")
    public void updateTheVolunteerParticipateStateForVolunteer(@RequestParam("bookingId") int bookingId, @RequestParam("participantState") String participantState) {
        volunteerPhysicalEventService.updateTheVolunteerParticipateStateForVolunteer(bookingId, participantState);
    }

}


