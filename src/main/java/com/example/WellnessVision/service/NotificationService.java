package com.example.WellnessVision.service;


import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.repository.NormalUserRegisterRepository;
import com.example.WellnessVision.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotificationsForAnyUser(int owner_id) {
        return notificationRepository.getAllNotificationsForAnyUser(owner_id);
    }

    public Notification getOneNotificationsForAnyUser(int notification_id) {
        return notificationRepository.getOneNotificationsForAnyUser(notification_id);
    }

    public int getNotificationsCountForAnyUser(int owner_id) {
        return notificationRepository.getNotificationsCountForAnyUser(owner_id, "Unread");
    }

    public void updateNotificationReadStatus(int notification_id, String readState) {
        notificationRepository.updateNotificationReadStatus(notification_id, readState);
    }

    public void createNewNotificationForAllUsers(Notification notification) {
        notificationRepository.save(notification);
    }
}
