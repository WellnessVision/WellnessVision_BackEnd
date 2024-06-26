package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class NormalUser {
    @Id
    private int user_id;
    private String user_type;
    private String email;
    private LocalDate dob;
    private String firstName;
    private String lastName;
    private String preferences;
    private String province;
    private String password;


    public NormalUser(){
    }
    public NormalUser(int user_id, String user_type, String email, LocalDate dob, String firstName, String lastName, String preferences, String province, String password) {
        this.user_id = user_id;
        this.user_type = user_type;
        this.email = email;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferences = preferences;
        this.province = province;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
