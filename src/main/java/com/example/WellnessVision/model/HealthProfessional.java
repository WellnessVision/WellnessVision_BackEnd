package com.example.WellnessVision.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class HealthProfessional {
    @Id
    private int healthProfessionalId;
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
    private String password;
    private String profession;
    private String organization;
    private String regNo;
    private String ownership;
    private String certificateImage;
    private String otherVerificationPdf;
    private LocalDateTime requestTime;
    private LocalDateTime acceptTime;

    public HealthProfessional() {
    }

    public HealthProfessional(int healthProfessionalId, String firstName, String lastName, String address, String address2, String city, String district, String province, String zip, String email, String phoneNumber, String profilePicture, String password, String profession, String organization, String regNo, String ownership, String certificateImage, String otherVerificationPdf, LocalDateTime requestTime, LocalDateTime acceptTime) {
        this.healthProfessionalId = healthProfessionalId;
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
        this.password = password;
        this.profession = profession;
        this.organization = organization;
        this.regNo = regNo;
        this.ownership = ownership;
        this.certificateImage = certificateImage;
        this.otherVerificationPdf = otherVerificationPdf;
        this.requestTime = requestTime;
        this.acceptTime = acceptTime;
    }

    public int getHealthProfessionalId() {
        return healthProfessionalId;
    }

    public void setHealthProfessionalId(int healthProfessionalId) {
        this.healthProfessionalId = healthProfessionalId;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }

    public String getOtherVerificationPdf() {
        return otherVerificationPdf;
    }

    public void setOtherVerificationPdf(String otherVerificationPdf) {
        this.otherVerificationPdf = otherVerificationPdf;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }
}

