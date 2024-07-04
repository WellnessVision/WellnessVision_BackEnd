package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;
    private int owner_id;
    private String subject;
    @Column(length = 1000)
    private String message;
    private String read_state;
    private LocalDateTime notification_time;

    public Notification() {
    }

    public Notification(int owner_id, String subject, String message, String read_state, LocalDateTime notification_time) {
        this.owner_id = owner_id;
        this.subject = subject;
        this.message = message;
        this.read_state = read_state;
        this.notification_time = notification_time;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRead_state() {
        return read_state;
    }

    public void setRead_state(String read_state) {
        this.read_state = read_state;
    }

    public LocalDateTime getNotification_time() {
        return notification_time;
    }

    public void setNotification_time(LocalDateTime notification_time) {
        this.notification_time = notification_time;
    }
}
