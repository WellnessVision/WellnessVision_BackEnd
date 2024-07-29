package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.DeletedPhysicalEvent;
import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.service.DeletePhysicalEventService;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.HealthProfessionalPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.io.IOException;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class DeletePhysicalEventController {

    @Autowired
    private DeletePhysicalEventService deletePhysicalEventService;

    @GetMapping("/viewAllDeleteHealthProfessionalPhysicalEventForHP")
    public List<DeletedPhysicalEvent> viewAllDeleteHealthProfessionalPhysicalEventForHP(@RequestParam("hp_id") int hp_id, @RequestParam("searchCode") String searchCode) throws IOException {
        return deletePhysicalEventService.viewAllDeleteHealthProfessionalPhysicalEventForHP(hp_id, searchCode);
    }

    @GetMapping("/viewOneDeleteHealthProfessionalPhysicalEventForHP")
    public DeletedPhysicalEvent viewOneDeleteHealthProfessionalPhysicalEventForHP(@RequestParam("eventId") int event_id) throws IOException {
        return deletePhysicalEventService.viewOneDeleteHealthProfessionalPhysicalEventForHP(event_id);
    }

}
