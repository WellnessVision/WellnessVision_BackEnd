package com.example.WellnessVision.service;

import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.repository.PhysicalEventBookingPaymentRepository;
import com.example.WellnessVision.repository.PhysicalEventBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NormalUserPaymentService {

    @Autowired
    private final PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository;
    private final PhysicalEventBookingRepository physicalEventBookingRepository;

    public NormalUserPaymentService(PhysicalEventBookingPaymentRepository physicalEventBookingPaymentRepository, PhysicalEventBookingRepository physicalEventBookingRepository) {
        this.physicalEventBookingPaymentRepository = physicalEventBookingPaymentRepository;
        this.physicalEventBookingRepository = physicalEventBookingRepository;
    }

    public List<PhysicalEventBookingPayment> viewNormalUserPhysicalEventBookingPaymentForNU(int user_id) throws IOException {
        return physicalEventBookingPaymentRepository.viewNormalUserPhysicalEventBookingPaymentForNU(user_id);
    }

    public PhysicalEventBookingPayment viewOneNormalUserPhysicalEventBookingPaymentForNU(int payment_id) throws IOException {
        return physicalEventBookingPaymentRepository.viewOneNormalUserPhysicalEventBookingPaymentForNU(payment_id);
    }

    public PhysicalEventBooking viewMoneyReceiptsDetailsForOneNormalUserPhysicalEventBookingPaymentForNU(int booking_id) throws IOException {
        return physicalEventBookingRepository.PhysicalEventsBookingDetailsForUsers(booking_id);
    }

}
