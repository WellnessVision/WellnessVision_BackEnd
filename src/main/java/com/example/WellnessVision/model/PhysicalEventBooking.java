package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class PhysicalEventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private int userId;
    private int eventId;
    private String bookingState;
    private String eventState;
    private String participantId;
    private String participantState;
    private String accountNumber;
    private String accountOwnerName;
    private String branchName;
    private String BankName;

    public PhysicalEventBooking() {
    }

    public PhysicalEventBooking(int bookingId, int userId, int eventId, String bookingState, String eventState, String participantId, String participantState, String accountNumber, String accountOwnerName, String branchName, String bankName) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.bookingState = bookingState;
        this.eventState = eventState;
        this.participantId = participantId;
        this.participantState = participantState;
        this.accountNumber = accountNumber;
        this.accountOwnerName = accountOwnerName;
        this.branchName = branchName;
        BankName = bankName;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public void setParticipantState(String participantState) {
        this.participantState = participantState;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }
}
