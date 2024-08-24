package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.DeleteAppointmentSchedule;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeleteAppointmentScheduleRepository extends JpaRepository<DeleteAppointmentSchedule, Integer> {

}


