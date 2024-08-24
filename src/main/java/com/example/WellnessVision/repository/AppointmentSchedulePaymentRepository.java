package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentSchedulePaymentRepository extends JpaRepository<AppointmentSchedulePayment, Integer> {

}
