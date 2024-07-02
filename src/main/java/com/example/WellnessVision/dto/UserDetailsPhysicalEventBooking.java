package com.example.WellnessVision.dto;

public class UserDetailsPhysicalEventBooking {
    private int user_id;
    private int booking_id;

    public UserDetailsPhysicalEventBooking() {
    }
    public UserDetailsPhysicalEventBooking(int user_id, int booking_id) {
        this.user_id = user_id;
        this.booking_id = booking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

}
