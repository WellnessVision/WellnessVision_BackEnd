package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VolunteerPhysicalEventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private int eventId;
    private int volunteerId;
    private String bookingState;
    private String eventState;
    private String participantId;
    private String participantState;
    private String requestPosition;
    private String experiences;
    private String previousWorksPdf;
    private LocalDateTime requestTime;
    private LocalDateTime acceptTime;

    public VolunteerPhysicalEventBooking() {
    }

    public VolunteerPhysicalEventBooking(int eventId, int volunteerId, String bookingState, String eventState, String participantId, String participantState, String requestPosition, String experiences, String previousWorksPdf, LocalDateTime requestTime, LocalDateTime acceptTime) {
        this.eventId = eventId;
        this.volunteerId = volunteerId;
        this.bookingState = bookingState;
        this.eventState = eventState;
        this.participantId = participantId;
        this.participantState = participantState;
        this.requestPosition = requestPosition;
        this.experiences = experiences;
        this.previousWorksPdf = previousWorksPdf;
        this.requestTime = requestTime;
        this.acceptTime = acceptTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public int getVolunteerId() {
        return volunteerId;
    }
    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }
    public String getParticipantId() {
        return participantId;
    }
    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipantState() {
        return participantState;
    }

    public void setParticipantState(String participantState) {
        this.participantState = participantState;
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

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }
    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }
}
