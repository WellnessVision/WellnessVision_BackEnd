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

    public Room() {
    }
    public Room(String roomId, String roomType, int charge, double advancePercentage, String state) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.charge = charge;
        this.advancePercentage = advancePercentage;
        this.state = state;
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
}
