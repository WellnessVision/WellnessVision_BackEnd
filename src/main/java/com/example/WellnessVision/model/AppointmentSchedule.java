package com.example.WellnessVision.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppointmentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    private String roomId;
    private String title;
    private String roomType;
    private int sunDay;
    private int monDay;
    private int tueDay;
    private int wedDay;
    private int thuDay;
    private int friDay;
    private int satDay;
    private int startTime;
    private int endTime;
    private int duration;
    private int capacity;
    private int bookingPrice;
    private int totalHallCharge;
    private double advancePercentage;
    private int advancePayment;
    private int paymentId;
    private int hpId;
    private String dailyState;
    private String accountNumber;
    private String accountOwnerName;
    private String branchName;
    private String bankName;
    private int bookingCount;
    private LocalDate startUnavailableDate;
    private LocalDate endUnavailableDate;
    private LocalDate appointmentBookingCloseDate;
    private LocalDate dalyUnavailableDate;

    public AppointmentSchedule() {
    }

    public AppointmentSchedule(String roomId, String title, String roomType, int sunDay, int monDay, int tueDay, int wedDay, int thuDay, int friDay, int satDay, int startTime, int endTime, int duration, int capacity, int bookingPrice, int totalHallCharge, double advancePercentage, int advancePayment, int paymentId, int hpId, String dailyState, String accountNumber, String accountOwnerName, String branchName, String bankName, int bookingCount) {
        this.roomId = roomId;
        this.title = title;
        this.roomType = roomType;
        this.sunDay = sunDay;
        this.monDay = monDay;
        this.tueDay = tueDay;
        this.wedDay = wedDay;
        this.thuDay = thuDay;
        this.friDay = friDay;
        this.satDay = satDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.capacity = capacity;
        this.bookingPrice = bookingPrice;
        this.totalHallCharge = totalHallCharge;
        this.advancePercentage = advancePercentage;
        this.advancePayment = advancePayment;
        this.paymentId = paymentId;
        this.hpId = hpId;
        this.dailyState = dailyState;
        this.accountNumber = accountNumber;
        this.accountOwnerName = accountOwnerName;
        this.branchName = branchName;
        this.bankName = bankName;
        this.bookingCount = bookingCount;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getSunDay() {
        return sunDay;
    }

    public void setSunDay(int sunDay) {
        this.sunDay = sunDay;
    }

    public int getMonDay() {
        return monDay;
    }

    public void setMonDay(int monDay) {
        this.monDay = monDay;
    }

    public int getTueDay() {
        return tueDay;
    }

    public void setTueDay(int tueDay) {
        this.tueDay = tueDay;
    }

    public int getWedDay() {
        return wedDay;
    }

    public void setWedDay(int wedDay) {
        this.wedDay = wedDay;
    }

    public int getThuDay() {
        return thuDay;
    }

    public void setThuDay(int thuDay) {
        this.thuDay = thuDay;
    }

    public int getFriDay() {
        return friDay;
    }

    public void setFriDay(int friDay) {
        this.friDay = friDay;
    }

    public int getSatDay() {
        return satDay;
    }

    public void setSatDay(int satDay) {
        this.satDay = satDay;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public int getTotalHallCharge() {
        return totalHallCharge;
    }

    public void setTotalHallCharge(int totalHallCharge) {
        this.totalHallCharge = totalHallCharge;
    }

    public double getAdvancePercentage() {
        return advancePercentage;
    }

    public void setAdvancePercentage(double advancePercentage) {
        this.advancePercentage = advancePercentage;
    }

    public int getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(int advancePayment) {
        this.advancePayment = advancePayment;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getHpId() {
        return hpId;
    }

    public void setHpId(int hpId) {
        this.hpId = hpId;
    }

    public String getDailyState() {
        return dailyState;
    }

    public void setDailyState(String dailyState) {
        this.dailyState = dailyState;
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

    public int getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(int bookingCount) {
        this.bookingCount = bookingCount;
    }

    public LocalDate getStartUnavailableDate() {
        return startUnavailableDate;
    }

    public void setStartUnavailableDate(LocalDate startUnavailableDate) {
        this.startUnavailableDate = startUnavailableDate;
    }

    public LocalDate getEndUnavailableDate() {
        return endUnavailableDate;
    }

    public void setEndUnavailableDate(LocalDate endUnavailableDate) {
        this.endUnavailableDate = endUnavailableDate;
    }

    public LocalDate getAppointmentBookingCloseDate() {
        return appointmentBookingCloseDate;
    }

    public void setAppointmentBookingCloseDate(LocalDate appointmentBookingCloseDate) {
        this.appointmentBookingCloseDate = appointmentBookingCloseDate;
    }

    public LocalDate getDalyUnavailableDate() {
        return dalyUnavailableDate;
    }

    public void setDalyUnavailableDate(LocalDate dalyUnavailableDate) {
        this.dalyUnavailableDate = dalyUnavailableDate;
    }
}
