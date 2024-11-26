package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Appointment;
import com.example.WellnessVision.service.HealthProfessionalAppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5173")  // Allows frontend to access this endpoint (adjust as needed)
public class HealthProfessionalAppointmentsController {

    @Autowired
    private HealthProfessionalAppointmentsService appointmentsService;

    // Endpoint to get dummy appointments
    @GetMapping("/dummy/{hpId}")
    public List<Appointment> getDummyAppointments(@PathVariable int hpId) {
        return appointmentsService.getDummyAppointmentsForHealthProfessional(hpId);
    }
}
