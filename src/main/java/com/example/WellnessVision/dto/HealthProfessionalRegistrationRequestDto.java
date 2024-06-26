package com.example.WellnessVision.dto;

import org.springframework.web.multipart.MultipartFile;

public class HealthProfessionalRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String address;
    private String address2;
    private String city;
    private String district;
    private String province;
    private String zip;
    private String email;
    private MultipartFile profilePicture;
    private String password;
    private String profession;
    private String organization;
    private String regNo;
    private String ownership;
    private MultipartFile certificateImage;
    private MultipartFile otherVerificationPdf;

    public HealthProfessionalRegistrationRequestDto() {
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

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
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

    public MultipartFile getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(MultipartFile certificateImage) {
        this.certificateImage = certificateImage;
    }

    public MultipartFile getOtherVerificationPdf() {
        return otherVerificationPdf;
    }

    public void setOtherVerificationPdf(MultipartFile otherVerificationPdf) {
        this.otherVerificationPdf = otherVerificationPdf;
    }
}
