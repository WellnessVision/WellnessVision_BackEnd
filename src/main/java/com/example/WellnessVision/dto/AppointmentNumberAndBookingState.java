package com.example.WellnessVision.dto;

public class AppointmentNumberAndBookingState {
    private int appointmentNumber;
    private int bookingId;
    private String bookingSate;

    public AppointmentNumberAndBookingState() {
    }
    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getBookingSate() {
        return bookingSate;
    }

    public void setBookingSate(String bookingSate) {
        this.bookingSate = bookingSate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}

