package com.example.WellnessVision.dto;


public class HallAvailability {

    private String hall_id;
    private String hall_type;
    private int capacity;
    private int charge;
    private int event_id;
    private double advance_percentage;

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

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public double getAdvance_percentage() {
        return advance_percentage;
    }

    public void setAdvance_percentage(double advance_percentage) {
        this.advance_percentage = advance_percentage;
    }
}
