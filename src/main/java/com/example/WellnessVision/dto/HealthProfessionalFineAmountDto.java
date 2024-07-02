package com.example.WellnessVision.dto;

import java.time.LocalDate;

public class HealthProfessionalFineAmountDto {
    private LocalDate today;
    private LocalDate eventDate;
    private LocalDate twoDaysBeforeDate;
    private  int fineAmount;
    private  int depositAmount;
    private  int hallCharge;
    private  int advancePayment;
    private  Double advancePaymentPercentage;
    private String fineAmountDetails;

    public HealthProfessionalFineAmountDto() {
    }

    public HealthProfessionalFineAmountDto(LocalDate today, LocalDate eventDate, LocalDate twoDaysBeforeDate, int fineAmount, int depositAmount, int hallCharge, int advancePayment, Double advancePaymentPercentage, String fineAmountDetails) {
        this.today = today;
        this.eventDate = eventDate;
        this.twoDaysBeforeDate = twoDaysBeforeDate;
        this.fineAmount = fineAmount;
        this.depositAmount = depositAmount;
        this.hallCharge = hallCharge;
        this.advancePayment = advancePayment;
        this.advancePaymentPercentage = advancePaymentPercentage;
        this.fineAmountDetails = fineAmountDetails;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDate getTwoDaysBeforeDate() {
        return twoDaysBeforeDate;
    }

    public void setTwoDaysBeforeDate(LocalDate twoDaysBeforeDate) {
        this.twoDaysBeforeDate = twoDaysBeforeDate;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getHallCharge() {
        return hallCharge;
    }

    public void setHallCharge(int hallCharge) {
        this.hallCharge = hallCharge;
    }

    public int getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(int advancePayment) {
        this.advancePayment = advancePayment;
    }

    public Double getAdvancePaymentPercentage() {
        return advancePaymentPercentage;
    }

    public void setAdvancePaymentPercentage(Double advancePaymentPercentage) {
        this.advancePaymentPercentage = advancePaymentPercentage;
    }

    public String getFineAmountDetails() {
        return fineAmountDetails;
    }

    public void setFineAmountDetails(String fineAmountDetails) {
        this.fineAmountDetails = fineAmountDetails;
    }
}
