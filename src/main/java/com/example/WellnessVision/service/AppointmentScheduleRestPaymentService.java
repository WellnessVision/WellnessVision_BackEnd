package com.example.WellnessVision.service;


import com.example.WellnessVision.model.AppointmentScheduleRestPayment;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.repository.AppointmentScheduleRestPaymentRepository;
import com.example.WellnessVision.repository.NormalUserRegisterRepository;
import com.example.WellnessVision.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentScheduleRestPaymentService {

    @Autowired
    private AppointmentScheduleRestPaymentRepository appointmentScheduleRestPaymentRepository;

    public void createNewAppointmentScheduleRestPaymentForHp(AppointmentScheduleRestPayment appointmentScheduleRestPayment) {
        appointmentScheduleRestPaymentRepository.save(appointmentScheduleRestPayment);
    }
}
