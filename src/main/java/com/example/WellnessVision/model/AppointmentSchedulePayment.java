package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AppointmentSchedulePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;
    private int appointment_schedule_id;
    private LocalDate payment_date;
    private LocalDateTime payment_time;
    private int amount;
    private String payment_state;
    private String payment_description;
    private int hp_id;
    private String appointment_schedule_state;

    public AppointmentSchedulePayment() {
    }

    public AppointmentSchedulePayment(int payment_id, int appointment_schedule_id, LocalDate payment_date, LocalDateTime payment_time, int amount, String payment_state, String payment_description, int hp_id, String appointment_schedule_state) {
        this.payment_id = payment_id;
        this.appointment_schedule_id = appointment_schedule_id;
        this.payment_date = payment_date;
        this.payment_time = payment_time;
        this.amount = amount;
        this.payment_state = payment_state;
        this.payment_description = payment_description;
        this.hp_id = hp_id;
        this.appointment_schedule_state = appointment_schedule_state;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getAppointment_schedule_id() {
        return appointment_schedule_id;
    }

    public void setAppointment_schedule_id(int appointment_schedule_id) {
        this.appointment_schedule_id = appointment_schedule_id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(String payment_state) {
        this.payment_state = payment_state;
    }

    public String getPayment_description() {
        return payment_description;
    }

    public void setPayment_description(String payment_description) {
        this.payment_description = payment_description;
    }

    public int getHp_id() {
        return hp_id;
    }

    public void setHp_id(int hp_id) {
        this.hp_id = hp_id;
    }

    public String getAppointment_schedule_state() {
        return appointment_schedule_state;
    }

    public void setAppointment_schedule_state(String appointment_schedule_state) {
        this.appointment_schedule_state = appointment_schedule_state;
    }
}
