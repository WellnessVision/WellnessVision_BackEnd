package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.PhysicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalEventOrderRepository extends JpaRepository<PhysicalEvent, Integer> {
}
