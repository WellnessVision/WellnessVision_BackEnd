package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class VolunteerPhysicalEventRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private int volunteerId;
    private int eventId;
    private String requestPosition;
    private String experiences;
    private String previousWorksPdf;
    private LocalDateTime requestTime;

    public VolunteerPhysicalEventRequest() {
    }
    public VolunteerPhysicalEventRequest(int volunteerId, int eventId, String requestPosition, String experiences, String previousWorksPdf, LocalDateTime requestTime) {
        this.volunteerId = volunteerId;
        this.eventId = eventId;
        this.requestPosition = requestPosition;
        this.experiences = experiences;
        this.previousWorksPdf = previousWorksPdf;
        this.requestTime = requestTime;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getRequestPosition() {
        return requestPosition;
    }

    public void setRequestPosition(String requestPosition) {
        this.requestPosition = requestPosition;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getPreviousWorksPdf() {
        return previousWorksPdf;
    }

    public void setPreviousWorksPdf(String previousWorksPdf) {
        this.previousWorksPdf = previousWorksPdf;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
