package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.service.NormalUserDashboardService;
import com.example.WellnessVision.service.NormalUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")

public class NormalUserDashboardController {

    @Autowired
    private NormalUserRegisterService normalUserRegisterService;

    @Autowired
    private NormalUserDashboardService normalUserDashboardService;

    @GetMapping("/getNormalUserDetails")
    public Optional<NormalUser> getNormalUserDetails(@RequestParam("userId") int user_id){

        return normalUserRegisterService.getUserDetails(user_id);
    }

    @GetMapping("/getPhysicalEvents")
    public List<PhysicalEvent> getAllPhysicalEventsForUser() {
        return (List<PhysicalEvent>) normalUserDashboardService.getAllPhysicalEvents();
    }

    @GetMapping("/getBookedPhysicalEventsByUserId")
    public List<PhysicalEvent> getAllBookedPhysicalEventsByUserIdForUser(@RequestParam("userId") int user_id) {
        return (List<PhysicalEvent>) normalUserDashboardService.getAllBookedPhysicalEventsByUserId(user_id);
    }



}
