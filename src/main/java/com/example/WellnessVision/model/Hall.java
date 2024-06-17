package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hall {
    @Id
    private String hall_id;
    private String hall_type;
    private int capacity;
    private int charge;
    private String state;

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
}
