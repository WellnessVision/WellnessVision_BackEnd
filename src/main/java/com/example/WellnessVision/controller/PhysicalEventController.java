package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.EventID;
import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.dto.HealthProfessionalFineAmountDto;
import com.example.WellnessVision.dto.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PutMapping("/physicalEvent")
    public void cancelHallBooking(@RequestBody EventID eventID) {
        service.cancelHallBooking(eventID.getEvent_id());
    }

    @GetMapping("/viewPhysicalEvent")
    public List<com.example.WellnessVision.model.PhysicalEvent> getPhysicalEventForHP(@RequestParam("hp_id") int hp_id, @RequestParam("eventState") String eventState) {
        return (List<com.example.WellnessVision.model.PhysicalEvent>) service.getPhysicalEventForHP(hp_id, eventState);
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

}
