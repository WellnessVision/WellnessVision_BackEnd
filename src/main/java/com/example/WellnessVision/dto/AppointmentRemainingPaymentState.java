package com.example.WellnessVision.dto;

import java.time.LocalDate;

public class AppointmentRemainingPaymentState {

    private int remainingPayment;
    private int income;
    private int totalRoomCharge;
    private int remainingAdvance;
    private String remainingPaymentState;
    private LocalDate newPaymentStartDate;
    private LocalDate startDate;
    private LocalDate endDate;

    public AppointmentRemainingPaymentState() {
    }

    public int getRemainingPayment() {
        return remainingPayment;
    }

    public void setRemainingPayment(int remainingPayment) {
        this.remainingPayment = remainingPayment;
    }

    public String getRemainingPaymentState() {
        return remainingPaymentState;
    }

    public void setRemainingPaymentState(String remainingPaymentState) {
        this.remainingPaymentState = remainingPaymentState;
    }

    public LocalDate getNewPaymentStartDate() {
        return newPaymentStartDate;
    }

    public void setNewPaymentStartDate(LocalDate newPaymentStartDate) {
        this.newPaymentStartDate = newPaymentStartDate;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getTotalRoomCharge() {
        return totalRoomCharge;
    }

    public void setTotalRoomCharge(int totalRoomCharge) {
        this.totalRoomCharge = totalRoomCharge;
    }

    public int getRemainingAdvance() {
        return remainingAdvance;
    }

    public void setRemainingAdvance(int remainingAdvance) {
        this.remainingAdvance = remainingAdvance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

