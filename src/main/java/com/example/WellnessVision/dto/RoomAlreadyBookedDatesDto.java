package com.example.WellnessVision.dto;

import java.time.LocalDate;

public class RoomAlreadyBookedDatesDto {

    private LocalDate bookedDated;

    public RoomAlreadyBookedDatesDto() {
    }

    public RoomAlreadyBookedDatesDto(LocalDate bookedDated) {
        this.bookedDated = bookedDated;
    }

    public LocalDate getBookedDated() {
        return bookedDated;
    }

    public void setBookedDated(LocalDate bookedDated) {
        this.bookedDated = bookedDated;
    }
}
