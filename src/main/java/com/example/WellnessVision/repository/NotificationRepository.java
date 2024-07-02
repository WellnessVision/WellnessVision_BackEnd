package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
