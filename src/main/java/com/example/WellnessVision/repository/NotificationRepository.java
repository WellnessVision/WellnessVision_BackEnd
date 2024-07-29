package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM notification WHERE owner_id = ?1 ORDER BY notification_time DESC ", nativeQuery = true)
    List<Notification> getAllNotificationsForAnyUser(int owner_id);

    @Query(value = "SELECT * FROM notification WHERE notification_id = ?1", nativeQuery = true)
    Notification getOneNotificationsForAnyUser(int notification_id);

    @Query(value = "SELECT COUNT(*) AS notifications_count FROM notification WHERE owner_id = ?1 AND read_state = ?2", nativeQuery = true)
    int getNotificationsCountForAnyUser(int owner_id, String read_state);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notification SET read_state = ?2 WHERE notification_id = ?1", nativeQuery = true)
    void updateNotificationReadStatus(int notification_id, String readState);

}
