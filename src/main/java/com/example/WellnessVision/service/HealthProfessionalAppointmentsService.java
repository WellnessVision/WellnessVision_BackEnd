package com.example.WellnessVision.service;

import com.example.WellnessVision.model.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthProfessionalAppointmentsService {

    // Method to return dummy appointment data
    public List<Appointment> getDummyAppointmentsForHealthProfessional(int hpId) {
        List<Appointment> dummyAppointments = new ArrayList<>();

        // Create some dummy appointments
        Appointment appointment1 = new Appointment(1, hpId, "John Doe", LocalDateTime.now().plusDays(1), "General Checkup");
        Appointment appointment2 = new Appointment(2, hpId, "Jane Smith", LocalDateTime.now().plusDays(2), "Dental Consultation");
        
        dummyAppointments.add(appointment1);
        dummyAppointments.add(appointment2);
        
        return dummyAppointments;
    }
}
