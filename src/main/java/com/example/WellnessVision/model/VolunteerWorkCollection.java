package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VolunteerWorkCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workCollectionId;
    private int volunteerId;
    private String attachment;
    private String subject;
    @Column(length = 1000)
    private String description;
    private LocalDateTime modifiedTime;


    public VolunteerWorkCollection() {
    }

    public VolunteerWorkCollection(int volunteerId, String attachment, String subject, String description, LocalDateTime modifiedTime) {
        this.volunteerId = volunteerId;
        this.attachment = attachment;
        this.subject = subject;
        this.description = description;
        this.modifiedTime = modifiedTime;
    }

    public int getWorkCollectionId() {
        return workCollectionId;
    }

    public void setWorkCollectionId(int workCollectionId) {
        this.workCollectionId = workCollectionId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
