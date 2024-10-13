package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AppointmentScheduleRestPayment {
    @Id
    private int appointmentScheduleId;
    private LocalDate newPaymentStartDate;
    private int remainingAdvancePayment;

    public AppointmentScheduleRestPayment() {
    }

    public AppointmentScheduleRestPayment(int appointmentScheduleId, LocalDate newPaymentStartDate, int remainingAdvancePayment) {
        this.appointmentScheduleId = appointmentScheduleId;
        this.newPaymentStartDate = newPaymentStartDate;
        this.remainingAdvancePayment = remainingAdvancePayment;
    }

    public int getAppointmentScheduleId() {
        return appointmentScheduleId;
    }

    public void setAppointmentScheduleId(int appointmentScheduleId) {
        this.appointmentScheduleId = appointmentScheduleId;
    }

    public LocalDate getNewPaymentStartDate() {
        return newPaymentStartDate;
    }

    public void setNewPaymentStartDate(LocalDate newPaymentStartDate) {
        this.newPaymentStartDate = newPaymentStartDate;
    }

    public int getRemainingAdvancePayment() {
        return remainingAdvancePayment;
    }

    public void setRemainingAdvancePayment(int remainingAdvancePayment) {
        this.remainingAdvancePayment = remainingAdvancePayment;
    }
}
