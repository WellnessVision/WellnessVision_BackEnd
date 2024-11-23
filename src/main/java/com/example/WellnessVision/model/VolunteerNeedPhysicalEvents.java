package com.example.WellnessVision.model;

import jakarta.persistence.*;

@Entity
public class VolunteerNeedPhysicalEvents {
    @Id
    private int eventId;
    private String volunteerType;
    private String volunteerDescription;

    public VolunteerNeedPhysicalEvents() {
    }

    public VolunteerNeedPhysicalEvents(int eventId, String volunteerType, String volunteerDescription) {
        this.eventId = eventId;
        this.volunteerType = volunteerType;
        this.volunteerDescription = volunteerDescription;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getVolunteerType() {
        return volunteerType;
    }

    public void setVolunteerType(String volunteerType) {
        this.volunteerType = volunteerType;
    }

    public String getVolunteerDescription() {
        return volunteerDescription;
    }

    public void setVolunteerDescription(String volunteerDescription) {
        this.volunteerDescription = volunteerDescription;
    }
}
