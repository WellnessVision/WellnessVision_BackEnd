package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PhysicalEventPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    private int physical_event_id;
    private LocalDate payment_date;
    private LocalDateTime payment_time;
    private int amount;
    private String payment_state;

    public PhysicalEventPayment() {
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getPhysical_event_id() {
        return physical_event_id;
    }

    public void setPhysical_event_id(int physical_event_id) {
        this.physical_event_id = physical_event_id;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }

    public LocalDateTime getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(LocalDateTime payment_time) {
        this.payment_time = payment_time;
    }

    public String getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(String payment_state) {
        this.payment_state = payment_state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
