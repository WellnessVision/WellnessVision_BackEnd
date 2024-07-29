package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.service.LoginService;
import com.example.WellnessVision.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")

public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getAllNotificationsForAnyUser")
    public List<Notification> getAllNotificationsForAnyUser(@RequestParam("ownerId") int owner_id) {
        return notificationService.getAllNotificationsForAnyUser(owner_id);
    }

    @GetMapping("/getOneNotificationsForAnyUser")
    public Notification getOneNotificationsForAnyUser(@RequestParam("notificationId") int notification_id) {
        return notificationService.getOneNotificationsForAnyUser(notification_id);
    }

    @GetMapping("/getNotificationsCountForAnyUser")
    public int getNotificationsCountForAnyUser(@RequestParam("ownerId") int owner_id) {
        return notificationService.getNotificationsCountForAnyUser(owner_id);
    }

    @PutMapping("/updateNotificationReadStatus")
    public void updateNotificationReadStatus(@RequestParam("notificationId") int notification_id, @RequestParam("readState") String readState)
    {
        notificationService.updateNotificationReadStatus(notification_id, readState);
    }

}