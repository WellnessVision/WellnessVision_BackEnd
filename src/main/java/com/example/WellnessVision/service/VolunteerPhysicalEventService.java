package com.example.WellnessVision.service;


import com.example.WellnessVision.dto.VolunteerRequestForPhysicalEventsDto;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VolunteerPhysicalEventService {

    @Autowired
    private VolunteerPhysicalEventRepository volunteerPhysicalEventRepository;

    @Autowired
    private NormalUserGetPhysicalEventsRepository normalUserGetPhysicalEventsRepository;

    @Autowired
    private VolunteerPhysicalEventBookingRepository volunteerPhysicalEventBookingRepository;

    @Autowired
    private ChatBoxVolunteerHealthProfessionalRepository chatBoxVolunteerHealthProfessionalRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    VolunteerPhysicalEventRequestRepository volunteerPhysicalEventRequestRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    VolunteerWorkCollectionRepository volunteerWorkCollectionRepository;

    @Autowired
    PhysicalEventOrderRepository physicalEventOrderRepository;

    public Volunteer getVolunteerDetails(int volunteerId){
        return volunteerPhysicalEventRepository.getVolunteerDetails(volunteerId);
    }

    public List<PhysicalEvent> getVolunteerUpcomingPhysicalEventsForVolunteers(int volunteerId, String bookingState, String eventState){
        return normalUserGetPhysicalEventsRepository.getVolunteerUpcomingPhysicalEventsForVolunteers(volunteerId, bookingState, eventState);
    }

    public VolunteerPhysicalEventBooking getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer(int VolunteerId, int eventId, String bookingState){
        return volunteerPhysicalEventBookingRepository.getBookingDetailsBookedUpcomingVolunteerPhysicalEventsForVolunteer(VolunteerId, eventId, bookingState);
    }
    public List<ChatBoxVolunteerHealthProfessional> getChatBoxForVolunteerAndPhysicalEventsForVolunteer(int VolunteerId, int eventId){
        return chatBoxVolunteerHealthProfessionalRepository.getChatBoxForVolunteerAndPhysicalEventsForVolunteer(VolunteerId, eventId);
    }

    public List<ChatBoxVolunteerHealthProfessional> getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int replyMessageId){
        return chatBoxVolunteerHealthProfessionalRepository.getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(replyMessageId);
    }

    public void newVolunteerMessageToHpPhysicalEventsForVolunteer(int VolunteerId, String userEmail, int eventId, String sender, String messageFlag, String message, String attachmentFlag, MultipartFile attachment, String replyFlag, String replyMessage, String replyMessageAttachmentFlag){

        String attachmentLink = null;

       if(Objects.equals(attachmentFlag, "Yes")){
           ResponseEntity<String> imageLinkResponse = fileUploadService.uploadFile(attachment, userEmail);
           if (imageLinkResponse.getStatusCode() == HttpStatus.OK && imageLinkResponse.getBody() != null) {
               attachmentLink = imageLinkResponse.getBody();
           }
       }

        ChatBoxVolunteerHealthProfessional chatBoxVolunteerHealthProfessional = new  ChatBoxVolunteerHealthProfessional(
                eventId,
                VolunteerId,
                sender,
                "Unseen",
                messageFlag,
                message,
                attachmentFlag,
                attachmentLink,
                replyFlag,
                replyMessage,
                replyMessageAttachmentFlag,
                LocalDateTime.now(),
                "No"
        );
        chatBoxVolunteerHealthProfessionalRepository.save(chatBoxVolunteerHealthProfessional);

        if(Objects.equals(sender, "V")){
            int hpId = physicalEventOrderRepository.getHpIdByEventIdForHp(eventId).getHp_id();
            String participantId = volunteerPhysicalEventBookingRepository.getParticipantIdByVolunteerIdForVolunteer(VolunteerId, eventId).getParticipantId();

            Notification notification = new Notification(
                    hpId,
                    "New Volunteer Message",
                    "You got a new volunteer message from participantId: " + participantId + " for eventId: " + eventId + ". You can see the chat under the upcoming physical event volunteer list",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);

        }

        if(Objects.equals(sender, "HP")){

            Notification notification = new Notification(
                    VolunteerId,
                    "New Message Received",
                    "You got a new message from eventId: " + eventId + ". You can see the chat under the my volunteer events",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);

        }

    }

    public void updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int replyMessageId, String newMessage){
        chatBoxVolunteerHealthProfessionalRepository.updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(replyMessageId, newMessage, "Unseen", "Yes");
        ChatBoxVolunteerHealthProfessional newChatBoxVolunteerHealthProfessional = chatBoxVolunteerHealthProfessionalRepository.getOneChatMessageDetailsForSendNotification(replyMessageId);

        if(Objects.equals(newChatBoxVolunteerHealthProfessional.getSender(), "V")){
            int hpId = physicalEventOrderRepository.getHpIdByEventIdForHp(newChatBoxVolunteerHealthProfessional.getEventId()).getHp_id();
            String participantId = volunteerPhysicalEventBookingRepository.getParticipantIdByVolunteerIdForVolunteer(newChatBoxVolunteerHealthProfessional.getVolunteerId(), newChatBoxVolunteerHealthProfessional.getEventId()).getParticipantId();

            Notification notification = new Notification(
                    hpId,
                    "Edit Volunteer Message",
                    "Edit the volunteer message by participantId: " + participantId + " for eventId: " + newChatBoxVolunteerHealthProfessional.getEventId() + ". You can see the chat under the upcoming physical event volunteer list",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);

        }

        if(Objects.equals(newChatBoxVolunteerHealthProfessional.getSender(), "HP")){

            Notification notification = new Notification(
                    newChatBoxVolunteerHealthProfessional.getVolunteerId(),
                    "One Message Edited",
                    "Edit the message by eventId: " + newChatBoxVolunteerHealthProfessional.getEventId() + ". You can see the chat under the my volunteer events",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationRepository.save(notification);

        }

    }

    public void deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int deleteMessageId){
        chatBoxVolunteerHealthProfessionalRepository.deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(deleteMessageId);
    }

    public void seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(int VolunteerId, int eventId, String sender){
        chatBoxVolunteerHealthProfessionalRepository.seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(VolunteerId, eventId, sender, "Seen");
    }

    public Integer getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(int VolunteerId, int eventId, String receiver){
        return  chatBoxVolunteerHealthProfessionalRepository.getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(VolunteerId, eventId, receiver, "Unseen");
    }

    public List<PhysicalEvent> getVolunteerNeedUpcomingPhysicalEventsForVolunteer(String eventState, String searchTitleCode, String searchVolunteerCode){
        String searchTitleCodeModify = searchTitleCode + "%";
        String searchVolunteerCodeModify = "%" + searchVolunteerCode + "%";
        return normalUserGetPhysicalEventsRepository.getVolunteerNeedUpcomingPhysicalEventsForVolunteer(eventState, searchTitleCodeModify, searchVolunteerCodeModify, "Need");
    }

    public String checkVolunteerRequestForPhysicalEventsForVolunteer(int volunteerId, int eventId){
        int requestState = volunteerPhysicalEventRequestRepository.checkVolunteerRequestForPhysicalEventsForVolunteer(volunteerId, eventId);
        if(requestState == 1){
            return "AlreadyRequest";
        }else {
            int volunteerState = volunteerPhysicalEventBookingRepository.checkAlreadyVolunteeredPhysicalEventsForVolunteer(volunteerId, eventId, "Booked");
            if(volunteerState == 1){
                return "AlreadyVolunteered";
            }else{
                return "NotRequest";
            }
        }
    }

    public void createVolunteerRequestForPhysicalEventsForVolunteer(int volunteerId, int eventId, int hpId, String requestPosition, String experiences, MultipartFile previousWorksPdf, String userEmail){
        String previousWorksPdfLink = "";
        ResponseEntity<String> imageLinkResponse = fileUploadService.uploadFile(previousWorksPdf, userEmail);
        if (imageLinkResponse.getStatusCode() == HttpStatus.OK && imageLinkResponse.getBody() != null) {
            previousWorksPdfLink = imageLinkResponse.getBody();
        }
        VolunteerPhysicalEventRequest volunteerPhysicalEventRequest = new VolunteerPhysicalEventRequest(
                volunteerId,
                eventId,
                requestPosition,
                experiences,
                previousWorksPdfLink,
                LocalDateTime.now() );

        volunteerPhysicalEventRequestRepository.save(volunteerPhysicalEventRequest);

        Notification notification = new Notification(
                hpId,
                "New Volunteer Request",
                "You got a new volunteer request for eventId: " + eventId + ". You can view more details under the event",
                "Unread",
                LocalDateTime.now()
        );
        notificationRepository.save(notification);
    }

    public void deleteTheVolunteerRequestForPhysicalEventsForVolunteer(int volunteerId, int eventId, int hpId){
        volunteerPhysicalEventRequestRepository.deleteTheVolunteerRequestForPhysicalEventsForVolunteer(volunteerId, eventId);

        Notification notification = new Notification(
                hpId,
                "Cancel Volunteer Request",
                "One volunteer request was canceled under eventId: " + eventId + ".",
                "Unread",
                LocalDateTime.now()
        );
        notificationRepository.save(notification);

    }

    public List<VolunteerRequestForPhysicalEventsDto> getVolunteerRequestForPhysicalEventsForHealthProfessionals(int eventId){
        List<VolunteerRequestForPhysicalEventsDto> volunteerRequestForPhysicalEventsDtoList = new ArrayList<>();
        List<VolunteerPhysicalEventRequest> volunteerPhysicalEventRequestList  = volunteerPhysicalEventRequestRepository.getVolunteerRequestForPhysicalEventsForHealthProfessionals(eventId);
        for (VolunteerPhysicalEventRequest oneVolunteerRequest : volunteerPhysicalEventRequestList) {
            Volunteer volunteer = volunteerRepository.getVolunteerDetailsByVolunteerId(oneVolunteerRequest.getVolunteerId());
            VolunteerRequestForPhysicalEventsDto newVolunteerRequestForPhysicalEventsDto = new VolunteerRequestForPhysicalEventsDto(
                    volunteer.getVolunteerId(),
                    oneVolunteerRequest.getRequestId(),
                    eventId,
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
                    oneVolunteerRequest.getRequestPosition(),
                    oneVolunteerRequest.getExperiences(),
                    oneVolunteerRequest.getPreviousWorksPdf(),
                    oneVolunteerRequest.getRequestTime()
            );
            volunteerRequestForPhysicalEventsDtoList.add(newVolunteerRequestForPhysicalEventsDto);
        }

        return volunteerRequestForPhysicalEventsDtoList;
    }

    public VolunteerRequestForPhysicalEventsDto getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(int requestId){
        VolunteerPhysicalEventRequest volunteerPhysicalEventRequest  = volunteerPhysicalEventRequestRepository.getOneVolunteerRequestForPhysicalEventsForHealthProfessionals(requestId);
        Volunteer volunteer = volunteerRepository.getVolunteerDetailsByVolunteerId(volunteerPhysicalEventRequest.getVolunteerId());
        VolunteerRequestForPhysicalEventsDto newVolunteerRequestForPhysicalEventsDto = new VolunteerRequestForPhysicalEventsDto(
                    volunteer.getVolunteerId(),
                    volunteerPhysicalEventRequest.getRequestId(),
                    volunteerPhysicalEventRequest.getEventId(),
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
                    volunteerPhysicalEventRequest.getRequestPosition(),
                    volunteerPhysicalEventRequest.getExperiences(),
                    volunteerPhysicalEventRequest.getPreviousWorksPdf(),
                    volunteerPhysicalEventRequest.getRequestTime()
            );

        return newVolunteerRequestForPhysicalEventsDto;
    }

    public List<VolunteerWorkCollection> getOneVolunteerWorkCollectionForVolunteer(int volunteerId){
        return volunteerWorkCollectionRepository.getOneVolunteerWorkCollectionForVolunteer(volunteerId);
    }

    public void addNewWorkToWorkCollectionForVolunteer(int volunteerId, String subject, String description, MultipartFile attachment, String userEmail){
        String previousWorksPdfLink = "";
        ResponseEntity<String> imageLinkResponse = fileUploadService.uploadFile(attachment, userEmail);
        if (imageLinkResponse.getStatusCode() == HttpStatus.OK && imageLinkResponse.getBody() != null) {
            previousWorksPdfLink = imageLinkResponse.getBody();
        }
        VolunteerWorkCollection newVolunteerWorkCollection = new VolunteerWorkCollection(
                volunteerId,
                previousWorksPdfLink,
                subject,
                description,
                LocalDateTime.now() );

        volunteerWorkCollectionRepository.save(newVolunteerWorkCollection);

    }

    public void editTextInWorkCollectionForVolunteer(int workCollectionId, String subject, String description){
        volunteerWorkCollectionRepository.editTextInWorkCollectionForVolunteer(workCollectionId, subject, description, LocalDateTime.now());
    }

    public void editAttachmentInWorkCollectionForVolunteer(int workCollectionId, MultipartFile attachment, String userEmail){
        String previousWorksPdfLink = "";
        ResponseEntity<String> imageLinkResponse = fileUploadService.uploadFile(attachment, userEmail);
        if (imageLinkResponse.getStatusCode() == HttpStatus.OK && imageLinkResponse.getBody() != null) {
            previousWorksPdfLink = imageLinkResponse.getBody();
        }

        volunteerWorkCollectionRepository.editAttachmentInWorkCollectionForVolunteer(workCollectionId, previousWorksPdfLink, LocalDateTime.now());

    }

    public void deleteWorkInWorkCollectionForVolunteer(int deletedWorkCollectionId){
        volunteerWorkCollectionRepository.deleteWorkInWorkCollectionForVolunteer(deletedWorkCollectionId);
    }

    public void updateTheVolunteerParticipateStateForVolunteer(int bookingId, String participantState){
        volunteerPhysicalEventBookingRepository.updateTheVolunteerParticipateStateForVolunteer(bookingId, participantState);
    }

}