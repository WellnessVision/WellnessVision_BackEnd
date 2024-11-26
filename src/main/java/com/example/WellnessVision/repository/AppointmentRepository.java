package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByHealthProfessionalId(int healthProfessionalId);
}
