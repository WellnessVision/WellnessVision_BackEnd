package com.example.WellnessVision.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
@Entity
public class PhysicalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;
    private String hall_id;
    private String eventTitle;
    private String finalEventType;
    private LocalDate date;
    private int startTime;
    private int endTime;
    private int finalDuration;
    private int capacity;
    private int ticketPrice;
    private String eventImage;
    private int hall_capacity;
    private int total_hall_charge;
    private double advance_percentage;
    private int advance_payment;
    private int payment_id;
    private String language;
    @Column(length = 1000)
    private String event_description;
    private int hp_id;
    private String event_state;
    private String accountNumber;
    private String accountOwnerName;
    private String branchName;
    private String bankName;
    private int ticketBookingCount;


    public PhysicalEvent() {
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getFinalEventType() {
        return finalEventType;
    }

    public void setFinalEventType(String finalEventType) {
        this.finalEventType = finalEventType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public int getFinalDuration() {
        return finalDuration;
    }

    public void setFinalDuration(int finalDuration) {
        this.finalDuration = finalDuration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public int getHall_capacity() {
        return hall_capacity;
    }

    public void setHall_capacity(int hall_capacity) {
        this.hall_capacity = hall_capacity;
    }

    public int getTotal_hall_charge() {
        return total_hall_charge;
    }

    public void setTotal_hall_charge(int total_hall_charge) {
        this.total_hall_charge = total_hall_charge;
    }

    public double getAdvance_percentage() {
        return advance_percentage;
    }

    public void setAdvance_percentage(double advance_percentage) {
        this.advance_percentage = advance_percentage;
    }

    public int getAdvance_payment() {
        return advance_payment;
    }

    public void setAdvance_payment(int advance_payment) {
        this.advance_payment = advance_payment;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public int getHp_id() {
        return hp_id;
    }

    public void setHp_id(int hp_id) {
        this.hp_id = hp_id;
    }

    public String getEvent_state() {
        return event_state;
    }

    public void setEvent_state(String event_state) {
        this.event_state = event_state;
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

    public int getTicketBookingCount() {
        return ticketBookingCount;
    }

    public void setTicketBookingCount(int ticketBookingCount) {
        this.ticketBookingCount = ticketBookingCount;
    }
}
