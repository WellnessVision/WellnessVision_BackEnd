package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.EventID;
import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.dto.PhysicalEvent;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:5173")

public class PhysicalEventController {

    @Autowired
    private PhysicalEventService service;

    @PostMapping("/physicalEvent")
    public HallAvailability getEventsByCapacityAndStatus(@RequestBody PhysicalEvent physicalEvent) throws IOException {
        String State = "Available";
        return service.getEventsByCapacityAndStatus(physicalEvent.getExpectedCapacity(),physicalEvent.getHallType(), State, physicalEvent.getDate(), physicalEvent.getStartTime(), physicalEvent.getEndTime(), physicalEvent.getEventTitle(), physicalEvent.getFinalEventType(), physicalEvent.getFinalDuration(), physicalEvent.getTicketPrice(), physicalEvent.getEventImage());
    }

    @PutMapping("/physicalEvent")
    public void cancelHallBooking(@RequestBody EventID eventID) {
        service.cancelHallBooking(eventID.getEvent_id());
    }

}
