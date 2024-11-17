package com.example.WellnessVision.dto;

import java.time.LocalDate;

public class RoomMaintenanceDateSetStateDto {

    private String setDateState;
    private LocalDate startDate;
    private LocalDate endDate;

    public RoomMaintenanceDateSetStateDto() {
    }

    public RoomMaintenanceDateSetStateDto(String setDateState, LocalDate startDate, LocalDate endDate) {
        this.setDateState = setDateState;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSetDateState() {
        return setDateState;
    }

    public void setSetDateState(String setDateState) {
        this.setDateState = setDateState;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
