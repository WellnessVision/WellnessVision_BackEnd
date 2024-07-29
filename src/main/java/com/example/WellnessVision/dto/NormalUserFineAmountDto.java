package com.example.WellnessVision.dto;

import java.time.LocalDate;

public class NormalUserFineAmountDto {
    private LocalDate today;
    private LocalDate eventDate;
    private LocalDate twoDaysBeforeDate;
    private  int ticketPrice;
    private  int fineAmount;
    private  int depositAmount;
    private  double finePercentage;
    private String fineAmountDetails;
    private String twoDaysBeforeState;

    public NormalUserFineAmountDto() {
    }

    public NormalUserFineAmountDto(LocalDate today, LocalDate eventDate, LocalDate twoDaysBeforeDate, int ticketPrice, int fineAmount, int depositAmount, double finePercentage, String fineAmountDetails, String twoDaysBeforeState) {
        this.today = today;
        this.eventDate = eventDate;
        this.twoDaysBeforeDate = twoDaysBeforeDate;
        this.ticketPrice = ticketPrice;
        this.fineAmount = fineAmount;
        this.depositAmount = depositAmount;
        this.finePercentage = finePercentage;
        this.fineAmountDetails = fineAmountDetails;
        this.twoDaysBeforeState = twoDaysBeforeState;
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

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
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

    public double getFinePercentage() {
        return finePercentage;
    }

    public void setFinePercentage(double finePercentage) {
        this.finePercentage = finePercentage;
    }

    public String getFineAmountDetails() {
        return fineAmountDetails;
    }

    public void setFineAmountDetails(String fineAmountDetails) {
        this.fineAmountDetails = fineAmountDetails;
    }

    public String getTwoDaysBeforeState() {
        return twoDaysBeforeState;
    }

    public void setTwoDaysBeforeState(String twoDaysBeforeState) {
        this.twoDaysBeforeState = twoDaysBeforeState;
    }
}
