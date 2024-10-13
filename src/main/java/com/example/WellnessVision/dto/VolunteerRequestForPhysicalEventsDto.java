package com.example.WellnessVision.dto;

import java.time.LocalDateTime;

public class VolunteerRequestForPhysicalEventsDto {
    private int volunteerId;
    private int requestId;
    private int eventId;
    private String firstName;
    private String lastName;
    private String address;
    private String address2;
    private String city;
    private String district;
    private String province;
    private String zip;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String requestPosition;
    private String experiences;
    private String previousWorksPdf;
    private LocalDateTime requestTime;

    public VolunteerRequestForPhysicalEventsDto() {
    }

    public VolunteerRequestForPhysicalEventsDto(int volunteerId, int requestId, int eventId, String firstName, String lastName, String address, String address2, String city, String district, String province, String zip, String email, String phoneNumber, String profilePicture, String requestPosition, String experiences, String previousWorksPdf, LocalDateTime requestTime) {
        this.volunteerId = volunteerId;
        this.requestId = requestId;
        this.eventId = eventId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.district = district;
        this.province = province;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.requestPosition = requestPosition;
        this.experiences = experiences;
        this.previousWorksPdf = previousWorksPdf;
        this.requestTime = requestTime;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRequestPosition() {
        return requestPosition;
    }

    public void setRequestPosition(String requestPosition) {
        this.requestPosition = requestPosition;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getPreviousWorksPdf() {
        return previousWorksPdf;
    }

    public void setPreviousWorksPdf(String previousWorksPdf) {
        this.previousWorksPdf = previousWorksPdf;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
