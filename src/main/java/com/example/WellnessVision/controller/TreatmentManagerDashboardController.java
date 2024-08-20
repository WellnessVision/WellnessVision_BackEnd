package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.ChatMessage;
import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.service.ChatMessageService;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

}

