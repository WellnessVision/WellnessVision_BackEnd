package com.example.WellnessVision.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

public class PhysicalEvent {

    private String eventTitle;
    private String finalEventType;
    private String hallType;
    private LocalDate date;
    private int startTime;
    private int endTime;
    private int finalDuration;
    private int expectedCapacity;
    private int ticketPrice;
    private String language;
    private String eventDescription;
    private int hpId;
    private String nullImage;
    private String accountNumber;
    private String accountOwnerName;
    private String branchName;
    private String bankName;

    public PhysicalEvent() {
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getFinalEventType() {
        return finalEventType;
    }

    public void setFinalEventType(String finalEventType) {
        this.finalEventType = finalEventType;
    }

    public String getHallType() {
        return hallType;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getFinalDuration() {
        return finalDuration;
    }

    public void setFinalDuration(int finalDuration) {
        this.finalDuration = finalDuration;
    }

    public int getExpectedCapacity() {
        return expectedCapacity;
    }

    public void setExpectedCapacity(int expectedCapacity) {
        this.expectedCapacity = expectedCapacity;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getNullImage() {
        return nullImage;
    }

    public void setNullImage(String nullImage) {
        this.nullImage = nullImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getHpId() {
        return hpId;
    }

    public void setHpId(int hpId) {
        this.hpId = hpId;
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
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
