package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class HealthProfessionalDashboardController {

    @Autowired
    private HealthProfessionalDashboardService healthProfessionalDashboardService;

    @GetMapping("/healthProfessionalDashboardProfileDetails")
    public Optional<HealthProfessional> healthProfessionalDashboardProfileDetails(@RequestParam("hpId") int hpId) throws IOException {
        return healthProfessionalDashboardService.healthProfessionalDashboardProfileDetails(hpId);
    }

    @GetMapping("/allHealthProfessionalDashboardProfileDetails")
    public List<HealthProfessional> allHealthProfessionalDashboardProfileDetails(String searchCode, String searchCode_2) throws IOException {
        return healthProfessionalDashboardService.allHealthProfessionalDashboardProfileDetails(searchCode, searchCode_2);
    }

}
