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
    private String phone;
    private String district;
    private String city;
    private String address;
    private String address2;
    private String firstName;
    private String lastName;
    private String preferences;
    private String province;
    private String zip;
    private String password;
    private String profilePic;



    public NormalUser(){
    }

    public NormalUser(int user_id, String user_type, String email, String phone, String district, String city, String address, String address2, String firstName, String lastName, String preferences, String province, String zip, String password, String profilePic) {
        this.user_id = user_id;
        this.user_type = user_type;
        this.email = email;
        this.phone = phone;
        this.district = district;
        this.city = city;
        this.address = address;
        this.address2 = address2;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferences = preferences;
        this.province = province;
        this.zip = zip;
        this.password = password;
        this.profilePic = profilePic;
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
