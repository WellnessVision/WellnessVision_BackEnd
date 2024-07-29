package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/adminDashboardProfileDetails")
    public Optional<AdminPrivilegeUser> healthProfessionalDashboardProfileDetails(@RequestParam("adminId") int adminId) throws IOException {
        return adminDashboardService.adminDashboardProfileDetails(adminId);
    }

}
