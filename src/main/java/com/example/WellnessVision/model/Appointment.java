package com.example.WellnessVision.model;

import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int healthProfessionalId;
    private String patientName;
    private LocalDateTime appointmentDate;
    private String appointmentDetails;

    // Constructor for dummy data
    public Appointment(int id, int healthProfessionalId, String patientName, LocalDateTime appointmentDate, String appointmentDetails) {
        this.id = id;
        this.healthProfessionalId = healthProfessionalId;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentDetails = appointmentDetails;
    }

    // Default constructor (needed by JPA)
    public Appointment() {}

}
