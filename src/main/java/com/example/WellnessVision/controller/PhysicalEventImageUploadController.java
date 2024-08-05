package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.service.FileUploadService;
import com.example.WellnessVision.service.NotificationService;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:5173")


public class PhysicalEventImageUploadController {

    @Autowired
    private FileUploadService service;

    @Autowired
    private PhysicalEventService physicalEventService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/physicalEventImageUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("event_id") int event_id, @RequestParam("hall_capacity") int hall_capacity, @RequestParam("total_hall_charge") int total_hall_charge, @RequestParam("advance_percentage") double advance_percentage, @RequestParam("advance_payment") int advance_payment, @RequestParam("userEmail") String userEmail, @RequestParam("hpId") int hpId, @RequestParam("title") String title, @RequestParam("eventType") String eventType, @RequestParam("ticketPrice") String ticketPrice, @RequestParam("language") String language, @RequestParam("description") String description, @RequestParam("accountNumber") String accountNumber, @RequestParam("accountHolderName") String accountHolderName, @RequestParam("branch") String branch, @RequestParam("bank") String bank) {
        try {
            PhysicalEventPayment physicalEventPayment = new PhysicalEventPayment();
            physicalEventPayment.setPhysical_event_id(event_id);
            physicalEventPayment.setPayment_date(LocalDate.now());
            physicalEventPayment.setPayment_time(LocalDateTime.now());
            physicalEventPayment.setAmount(advance_payment);
            physicalEventPayment.setHp_id(hpId);
            physicalEventPayment.setPayment_state("Payment");
            physicalEventPayment.setPayment_description("Book a hall for physical event");
            physicalEventPayment.setEvent_state("Available");
            int payment_id = physicalEventService.physicalEventPaymentSave(physicalEventPayment);
            Notification notification = new Notification(
                    hpId,
                    "Add New Physical Event",
                    "EventId : " + event_id + " event successfully created. Total hall charge : Rs."+ total_hall_charge + "/= and hall capacity : " + hall_capacity +
                            ". You successfully paid Advance payment : Rs." + advance_payment + "/=. You can view more details about this event under upcoming physical event category",
                    "Unread",
                    LocalDateTime.now()
            );
            notificationService.createNewNotificationForAllUsers(notification);
            ResponseEntity<String> imageLinkResponse = service.uploadFile(file, userEmail);
            if (imageLinkResponse.getStatusCode() == HttpStatus.OK && imageLinkResponse.getBody() != null) {
                physicalEventService.UploadEventImage(imageLinkResponse.getBody(), event_id, hall_capacity, total_hall_charge, advance_percentage, advance_payment, payment_id, title, eventType, ticketPrice, language, description, accountNumber, accountHolderName, branch, bank);
                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

