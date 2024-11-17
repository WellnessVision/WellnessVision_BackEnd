package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Room {
    @Id
    private String roomId;
    private String roomType;
    private int charge;
    private double advancePercentage;
    private String state;
    private LocalDate maintain_start_date;
    private LocalDate maintain_end_date;
    private LocalDate unavailable_date;

    public Room() {
    }

    public Room(String roomId, String roomType, int charge, double advancePercentage, String state, LocalDate maintain_start_date, LocalDate maintain_end_date, LocalDate unavailable_date) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.charge = charge;
        this.advancePercentage = advancePercentage;
        this.state = state;
        this.maintain_start_date = maintain_start_date;
        this.maintain_end_date = maintain_end_date;
        this.unavailable_date = unavailable_date;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public double getAdvancePercentage() {
        return advancePercentage;
    }

    public void setAdvancePercentage(double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getMaintain_start_date() {
        return maintain_start_date;
    }

    public void setMaintain_start_date(LocalDate maintain_start_date) {
        this.maintain_start_date = maintain_start_date;
    }

    public LocalDate getMaintain_end_date() {
        return maintain_end_date;
    }

    public void setMaintain_end_date(LocalDate maintain_end_date) {
        this.maintain_end_date = maintain_end_date;
    }

    public LocalDate getUnavailable_date() {
        return unavailable_date;
    }

    public void setUnavailable_date(LocalDate unavailable_date) {
        this.unavailable_date = unavailable_date;
    }
}
