package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class VolunteerRegistrationRequestController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private LoginService loginService;

    @Autowired
    VolunteerRegistrationRequestService volunteerRegistrationRequestService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/volunteerRegistrationRequestSave")
    public void volunteerRegistrationRequestSave(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address,
            @RequestParam("address2") String address2,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("province") String province,
            @RequestParam("zip") String zip,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("password") String password,
            @RequestParam("experiences") String experiences,
            @RequestParam("previousWorksPdf") MultipartFile previousWorksPdf) throws IOException {

        String ProfilePictureLink = null;
        String previousWorksPdfLink = null;

        ResponseEntity<String> ProfilePictureLinkResponse = fileUploadService.uploadFile(profilePicture,email);
        if (ProfilePictureLinkResponse.getStatusCode() == HttpStatus.OK && ProfilePictureLinkResponse.getBody() != null) {
            ProfilePictureLink = ProfilePictureLinkResponse.getBody();
        }

        ResponseEntity<String> certificateImageLinkResponse = fileUploadService.uploadFile(previousWorksPdf, email);
        if (certificateImageLinkResponse.getStatusCode() == HttpStatus.OK && certificateImageLinkResponse.getBody() != null) {
            previousWorksPdfLink = certificateImageLinkResponse.getBody();
        }

        String hashedPassword = PasswordHashingService.hashPassword(password);

        VolunteerRegistrationRequest newVolunteerRegistrationRequest = new VolunteerRegistrationRequest(
                firstName,
                lastName,
                address,
                address2,
                city,
                district,
                province,
                zip,
                email,
                phoneNumber,
                ProfilePictureLink,
                hashedPassword,
                experiences,
                previousWorksPdfLink,
                LocalDateTime.now()
        );

        volunteerRegistrationRequestService.volunteerRegistrationRequestSave(newVolunteerRegistrationRequest);

    }

    @GetMapping("/viewVolunteerRegistrationRequestForAdmin")
    public List<VolunteerRegistrationRequest> viewVolunteerRegistrationRequestForAdmin(){
        return volunteerRegistrationRequestService.viewVolunteerRegistrationRequestForAdmin();
    }

    @GetMapping("/viewOneVolunteerRegistrationRequestForAdmin")
    public VolunteerRegistrationRequest viewOneVolunteerRegistrationRequestForAdmin(@RequestParam("requestId") int requestId){
        return volunteerRegistrationRequestService.viewOneVolunteerRegistrationRequestForAdmin(requestId);
    }

    @PutMapping("/acceptVolunteerRegistrationRequest")
    public void acceptVolunteerRegistrationRequest(@RequestParam("requestId") int requestId) throws IOException {
        VolunteerRegistrationRequest newVolunteerRegistrationRequest = volunteerRegistrationRequestService.viewOneVolunteerRegistrationRequestForAdmin(requestId);
        Login login = new Login();
        login.setEmail(newVolunteerRegistrationRequest.getEmail());
        login.setPassword(newVolunteerRegistrationRequest.getPassword());
        login.setUser_type("V");
        loginService.registerLogin(login);
        Volunteer newVolunteer = new Volunteer(
                login.getId(),
                newVolunteerRegistrationRequest.getFirstName(),
                newVolunteerRegistrationRequest.getLastName(),
                newVolunteerRegistrationRequest.getAddress(),
                newVolunteerRegistrationRequest.getAddress2(),
                newVolunteerRegistrationRequest.getCity(),
                newVolunteerRegistrationRequest.getDistrict(),
                newVolunteerRegistrationRequest.getProvince(),
                newVolunteerRegistrationRequest.getZip(),
                newVolunteerRegistrationRequest.getEmail(),
                newVolunteerRegistrationRequest.getPhoneNumber(),
                newVolunteerRegistrationRequest.getProfilePicture(),
                newVolunteerRegistrationRequest.getPassword(),
                newVolunteerRegistrationRequest.getExperiences(),
                newVolunteerRegistrationRequest.getPreviousWorksPdf(),
                newVolunteerRegistrationRequest.getRequestTime(),
                LocalDateTime.now()
        );

        volunteerRegistrationRequestService.volunteerSave(newVolunteer);
        volunteerRegistrationRequestService.deleteVolunteerRegistrationRequest(requestId);

        Notification notification = new Notification(
                login.getId(),
                "Welcome to WellnessVision",
                "Congratulation!, Your registration request accepted by WellnessVision administration and now you can start your new journey with WellnessVision. " +
                        "Here you can volunteer physical events, participate physical events, Manage work collection...",
                "Unread",
                LocalDateTime.now()
        );
        notificationService.createNewNotificationForAllUsers(notification);

    }

}
