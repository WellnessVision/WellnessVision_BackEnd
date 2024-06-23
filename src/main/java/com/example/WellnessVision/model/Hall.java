package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Hall {
    @Id
    private String hall_id;
    private String hall_type;
    private int capacity;
    private int charge;
    private double advance_percentage;
    private String state;
    private LocalDate maintain_start_date;
    private LocalDate maintain_end_date;
    private LocalDate unavailable_date;

    public Hall() {
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getHall_type() {
        return hall_type;
    }

    public void setHall_type(String hall_type) {
        this.hall_type = hall_type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
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

    public double getAdvance_percentage() {
        return advance_percentage;
    }

    public void setAdvance_percentage(double advance_percentage) {
        this.advance_percentage = advance_percentage;
    }
}
