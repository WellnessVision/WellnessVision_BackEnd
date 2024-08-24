package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class UserAppointmentBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private LocalDate bookingDate;
    private LocalDateTime bookingTime;
    private int bookedAppointmentId;
    private int userId;
    private int appointmentNumber;
    private String bookingState;
    private String appointmentState;
    private String participantId;
    private String participantState;
    private String accountNumber;
    private String accountOwnerName;
    private String branchName;
    private String BankName;

    public UserAppointmentBooking() {
    }

    public UserAppointmentBooking(LocalDate bookingDate, LocalDateTime bookingTime, int bookedAppointmentId, int userId, int appointmentNumber, String bookingState, String appointmentState, String participantId, String participantState, String accountNumber, String accountOwnerName, String branchName, String bankName) {
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.bookedAppointmentId = bookedAppointmentId;
        this.userId = userId;
        this.appointmentNumber = appointmentNumber;
        this.bookingState = bookingState;
        this.appointmentState = appointmentState;
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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getBookedAppointmentId() {
        return bookedAppointmentId;
    }

    public void setBookedAppointmentId(int bookedAppointmentId) {
        this.bookedAppointmentId = bookedAppointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public String getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(String appointmentState) {
        this.appointmentState = appointmentState;
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
