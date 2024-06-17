package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.otherModel.PhysicalEvent;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")

public class PhysicalEventController {

    @Autowired
    private PhysicalEventService service;

    @PostMapping("/physicalEvent")
    public Optional<Hall> getEventsByCapacityAndStatus(@RequestBody PhysicalEvent physicalEvent) {
        String State = "Available";
        return service.getEventsByCapacityAndStatus(physicalEvent.getExpectedCapacity(),physicalEvent.getHallType(), State, physicalEvent.getDate(), physicalEvent.getStartTime(), physicalEvent.getEndTime(), physicalEvent.getEventTitle(), physicalEvent.getFinalEventType(), physicalEvent.getFinalDuration(), physicalEvent.getTicketPrice());
    }
}
