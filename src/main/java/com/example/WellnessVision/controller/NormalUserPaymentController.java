package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.NormalUserFineAmountDto;
import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.service.HealthProfessionalDashboardService;
import com.example.WellnessVision.service.HealthProfessionalPaymentsService;
import com.example.WellnessVision.service.NormalUserPaymentService;
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
public class NormalUserPaymentController {

    @Autowired
    private NormalUserPaymentService normalUserPaymentService;

    @GetMapping("/viewNormalUserPhysicalEventBookingPaymentForNU")
    public List<PhysicalEventBookingPayment> viewNormalUserPhysicalEventBookingPaymentForNU(@RequestParam("userId") int user_id) throws IOException {
        return normalUserPaymentService.viewNormalUserPhysicalEventBookingPaymentForNU(user_id);
    }

    @GetMapping("/viewOneNormalUserPhysicalEventBookingPaymentForNU")
    public PhysicalEventBookingPayment viewOneNormalUserPhysicalEventBookingPaymentForNU(@RequestParam("paymentId") int payment_id) throws IOException {
        return normalUserPaymentService.viewOneNormalUserPhysicalEventBookingPaymentForNU(payment_id);
    }

    @GetMapping("/viewMoneyReceiptsDetailsForOneNormalUserPhysicalEventBookingPaymentForNU")
    public PhysicalEventBooking viewMoneyReceiptsDetailsForOneNormalUserPhysicalEventBookingPaymentForNU(@RequestParam("bookingId") int booking_id) throws IOException {
        return normalUserPaymentService.viewMoneyReceiptsDetailsForOneNormalUserPhysicalEventBookingPaymentForNU(booking_id);
    }

}
