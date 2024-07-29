package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.HealthProfessionalPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class HealthProfessionalPaymentsController {

    @Autowired
    private HealthProfessionalPaymentsService healthProfessionalPaymentsService;

    @GetMapping("/viewHealthProfessionalPhysicalEventPaymentForHP")
    public List<PhysicalEventPayment> viewHealthProfessionalPhysicalEventPaymentForHP(@RequestParam("hpId") int hpId) throws IOException {
        return healthProfessionalPaymentsService.viewHealthProfessionalPhysicalEventPaymentForHP(hpId);
    }

    @GetMapping("/viewOneHealthProfessionalPhysicalEventPaymentForHP")
    public PhysicalEventPayment viewOneHealthProfessionalPhysicalEventPaymentForHP(@RequestParam("paymentId") int payment_id) throws IOException {
        return healthProfessionalPaymentsService.viewOneHealthProfessionalPhysicalEventPaymentForHP(payment_id);
    }

}
