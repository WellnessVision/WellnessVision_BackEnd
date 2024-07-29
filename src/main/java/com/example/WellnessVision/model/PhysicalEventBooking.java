package com.example.WellnessVision.model;

import jakarta.persistence.*;

@Entity
public class PhysicalEventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private int eventId;
    private int userId;
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

    public PhysicalEventBooking(int eventId, int userId, String bookingState, String eventState, String participantId, String participantState, String accountNumber, String accountOwnerName, String branchName, String bankName) {
        this.eventId = eventId;
        this.userId = userId;
        this.bookingState = bookingState;
        this.eventState = eventState;
        this.participantId = participantId;
        this.participantState = participantState;
        this.accountNumber = accountNumber;
        this.accountOwnerName = accountOwnerName;
        this.branchName = branchName;
        BankName = bankName;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }
}
