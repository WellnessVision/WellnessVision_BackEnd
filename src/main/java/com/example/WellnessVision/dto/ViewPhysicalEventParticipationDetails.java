package com.example.WellnessVision.dto;

public class ViewPhysicalEventParticipationDetails {

    private int user_id;
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
    private String profilePic;
    private int bookingId;
    private String bookingState;
    private String participantId;
    private String participantState;

    public ViewPhysicalEventParticipationDetails() {
    }

    public ViewPhysicalEventParticipationDetails(int user_id, String email, String phone, String district, String city, String address, String address2, String firstName, String lastName, String preferences, String province, String profilePic, int bookingId, String bookingState, String participantId, String participantState) {
        this.user_id = user_id;
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
        this.profilePic = profilePic;
        this.bookingId = bookingId;
        this.bookingState = bookingState;
        this.participantId = participantId;
        this.participantState = participantState;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipantState() {
        return participantState;
    }

    public void setParticipantState(String participantState) {
        this.participantState = participantState;
    }
}
