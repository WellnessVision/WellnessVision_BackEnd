package com.example.WellnessVision.repository;

import com.example.WellnessVision.dto.UserDetailsPhysicalEventBooking;
import com.example.WellnessVision.model.DeletedPhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeletedPhysicalEventRepository extends JpaRepository<DeletedPhysicalEvent, Integer> {
}
