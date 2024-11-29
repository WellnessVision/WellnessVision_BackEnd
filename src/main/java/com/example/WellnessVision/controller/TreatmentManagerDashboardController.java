package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.RoomAlreadyBookedDatesDto;
import com.example.WellnessVision.dto.RoomMaintenanceDateSetStateDto;
import com.example.WellnessVision.dto.VolunteerDetailsForPhysicalEventDto;
import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.ChatMessageService;
import com.example.WellnessVision.service.EventManagerService;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.configurationprocessor.json.JSONException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class TreatmentManagerDashboardController {

    @Autowired
    private HealthProfessionalDashboardService healthProfessionalDashboardService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private PhysicalEventService physicalEventService;

    @Autowired
    EventManagerService eventManagerService;

    @GetMapping("/getAllHealthProfessionals")
    public Optional<List<HealthProfessional>> getAllHealthProfessionals() throws IOException {
        return healthProfessionalDashboardService.getAllHealthProfessionals();
    }

    @GetMapping("/viewOneHealthProfessionalForEM")
    public Optional<HealthProfessional> getOneHealthProfessional(@RequestParam("hpId") int hpId) throws  IOException {
        return healthProfessionalDashboardService.healthProfessionalDashboardProfileDetails(hpId);
    }
    @GetMapping("/getAllPhysicalEventsForEM")
    public List<PhysicalEvent> getAllPhysicalEventsForEM(@RequestParam("hpId") int hp_id, @RequestParam("searchCode") String searchCode) throws  IOException {
        return physicalEventService.getAllPhysicalEventsForEM(hp_id, searchCode);
    }

    @PutMapping("/EMeditOnePhysicalEventDetail")
    public int  editOnePhysicalEventDetailByEM(@RequestParam ("eventId") int eventId, @RequestParam("eventTitle") String eventTitle, @RequestParam("event_description") String eventDescription){
        physicalEventService.editOnePhysicalEventDetail(eventId, eventTitle, eventDescription);
        return 0;
    }

    @PostMapping("/EMSaveChatWithHP")
    public ResponseEntity<?> EMSaveChatWithHP(@RequestParam ("hpId") int hpId, @RequestParam ("chatPayload") String chatPayload) throws JSONException {
        System.out.print("\nchatPayload");
        System.out.print(chatPayload);
        chatMessageService.EMSaveChatWithHPInDatabase(hpId, chatPayload);
        return ResponseEntity.ok("Chat printed");
    }

    @GetMapping("/getPrevChatWithEMAndHP")
    public Optional<List<ChatMessage>> getPrevChatWithEMAndHP(int hpId) throws JSONException {
        return chatMessageService.GetChatWithEMAndHPFromDatabase(hpId);
    }

    @GetMapping("/getEventManagerDetailsForEventManager")
    public AdminPrivilegeUser getEventManagerDetailsForEventManager(@RequestParam("eventManagerId") int eventManagerId) throws IOException {
        return eventManagerService.getEventManagerDetailsForEventManager(eventManagerId);
    }

    @GetMapping("/getPhysicalEventHallsForEventManager")
    public List<Hall> getPhysicalEventHallsForEventManager() throws IOException {
        return eventManagerService.getPhysicalEventHallsForEventManager();
    }

    @GetMapping("/getOnePhysicalEventHallDetailsForEventManager")
    public Hall getOnePhysicalEventHallDetailsForEventManager(@RequestParam("hallId") String hallId) throws IOException {
        return eventManagerService.getOnePhysicalEventHallDetailsForEventManager(hallId);
    }

    @GetMapping("/getPhysicalEventsOfOneHallForEventManager")
    public List<PhysicalEvent> getPhysicalEventsOfOneHallForEventManager(@RequestParam("hallId") String hallId) throws IOException {
        return eventManagerService.getPhysicalEventsOfOneHallForEventManager(hallId);
    }

    @GetMapping("/getAlreadyBookedDatesOfOneHallForEventManager")
    public List<RoomAlreadyBookedDatesDto> getAlreadyBookedDatesOfOneHallForEventManager(@RequestParam("hallId") String hallId) throws IOException {
        return eventManagerService.getAlreadyBookedDatesOfOneHallForEventManager(hallId);
    }

    @GetMapping("/checkAndSetMaintenanceDateOfHallForEventManager")
    public RoomMaintenanceDateSetStateDto checkAndSetMaintenanceDateOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) throws IOException {
        return eventManagerService.checkAndSetMaintenanceDateOfHallForEventManager(hallId, startDate, endDate);
    }

    @GetMapping("/checkAndSetUnavailableDateOfHallForEventManager")
    public RoomMaintenanceDateSetStateDto checkAndSetUnavailableDateOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("unavailableDate") LocalDate unavailableDate) throws IOException {
        return eventManagerService.checkAndSetUnavailableDateOfHallForEventManager(hallId, unavailableDate);
    }

    @PutMapping("/clearHallSetDateOfHallForEventManager")
    public void clearHallSetDateOfHallForEventManager(@RequestParam("hallId") String hallId) throws IOException {
        eventManagerService.clearHallSetDateOfHallForEventManager(hallId);
    }

    @PutMapping("/updateHallChargeAndAdvancePercentageOfHallForEventManager")
    public void updateHallChargeAndAdvancePercentageOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("charge") int charge, @RequestParam("advancePercentage") double advancePercentage) throws IOException {
        eventManagerService.updateHallChargeAndAdvancePercentageOfHallForEventManager(hallId, charge, advancePercentage);
    }

    @PutMapping("/changeHallStateOfHallForEventManager")
    public void changeHallStateOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("hallState") String hallState) throws IOException {
        eventManagerService.changeHallStateOfHallForEventManager(hallId, hallState);
    }

    @GetMapping("/getLastBookDateOfHallForEventManager")
    public RoomMaintenanceDateSetStateDto getLastBookDateOfHallForEventManager(@RequestParam("hallId") String hallId) throws IOException {
        return eventManagerService.getLastBookDateOfHallForEventManager(hallId);
    }

    @GetMapping("/CheckAndSetReduceHallCapacityOfHallForEventManager")
    public RoomMaintenanceDateSetStateDto CheckAndSetReduceHallCapacityOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("newCapacity") int newCapacity) throws IOException {
        return eventManagerService.CheckAndSetReduceHallCapacityOfHallForEventManager(hallId, newCapacity);
    }

    @PutMapping("/increasedHallCapacityOfHallForEventManager")
    public void increasedHallCapacityOfHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("newCapacity") int newCapacity) throws IOException {
        eventManagerService.increasedHallCapacityOfHallForEventManager(hallId, newCapacity);
    }

    @PostMapping("/addNewPhysicalEventHallForEventManager")
    public void addNewPhysicalEventHallForEventManager(@RequestParam("hallId") String hallId, @RequestParam("hallType") String hallType, @RequestParam("charge") int charge, @RequestParam("advancePercentage") double advancePercentage,  @RequestParam("capacity") int capacity) throws IOException {
        eventManagerService.addNewPhysicalEventHallForEventManager(hallId, hallType, charge, advancePercentage, capacity);
    }

}

