package com.example.WellnessVision.service;

import com.example.WellnessVision.model.PhysicalEventBookingPayment;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.repository.PhysicalEventPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HealthProfessionalPaymentsService {

    @Autowired
    private final PhysicalEventPaymentRepository physicalEventPaymentRepository;

    public HealthProfessionalPaymentsService(PhysicalEventPaymentRepository physicalEventPaymentRepository) {
        this.physicalEventPaymentRepository = physicalEventPaymentRepository;
    }

    public List<PhysicalEventPayment> viewHealthProfessionalPhysicalEventPaymentForHP(int hpId) throws IOException {
        return physicalEventPaymentRepository.viewHealthProfessionalPhysicalEventPaymentForHP(hpId);
    }

    public PhysicalEventPayment viewOneHealthProfessionalPhysicalEventPaymentForHP(int payment_id) throws IOException {
        return physicalEventPaymentRepository.viewOneHealthProfessionalPhysicalEventPaymentForHP(payment_id);
    }
}
