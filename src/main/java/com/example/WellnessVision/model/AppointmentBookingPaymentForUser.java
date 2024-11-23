package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AppointmentBookingPaymentForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private int appointmentBookingId;
    private int userId;
    private LocalDateTime paymentDate;
    private int amount;
    private String paymentState;
    private String paymentDescription;

    public AppointmentBookingPaymentForUser() {
    }

    public AppointmentBookingPaymentForUser(int appointmentBookingId, int userId, LocalDateTime paymentDate, int amount, String paymentState, String paymentDescription) {
        this.appointmentBookingId = appointmentBookingId;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentState = paymentState;
        this.paymentDescription = paymentDescription;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAppointmentBookingId() {
        return appointmentBookingId;
    }

    public void setAppointmentBookingId(int appointmentBookingId) {
        this.appointmentBookingId = appointmentBookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }
}
