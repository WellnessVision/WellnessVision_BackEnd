package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@CrossOrigin("http://localhost:5173")

public class NormalUserRegisterController {


    @Autowired
    private NormalUserRegisterService normalUserRegisterService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private FileUploadService imageUploadService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/userRegister")
    public String userRegister(
            @RequestParam("user_type") String user_type,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("preferences") String preferences,
            @RequestParam("district") String district,
            @RequestParam("address") String address,
            @RequestParam("address2") String address2,
            @RequestParam("city") String city,
            @RequestParam("province") String province,
            @RequestParam("zip") String zip,
            @RequestParam("password") String password,
            @RequestParam("profilePic") MultipartFile profilePic
            )
    {

        Login login = new Login();

        String hashPassword = PasswordHashingService.hashPassword(password);


        login.setUser_type(user_type);
        login.setEmail(email);
        login.setPassword(hashPassword);

        loginService.registerLogin(login);
        int user_id = login.getId();

        String profilePicLink = null;

        ResponseEntity<String> certificateImageLinkResponse = imageUploadService.uploadFile(profilePic, email);
        if (certificateImageLinkResponse.getStatusCode() == HttpStatus.OK && certificateImageLinkResponse.getBody() != null) {
            profilePicLink = certificateImageLinkResponse.getBody();
        }

        NormalUser normalUser = new NormalUser(user_id, user_type, email, phone, district, city, address, address2,  firstName, lastName, preferences, province, zip, hashPassword, profilePicLink);

        normalUserRegisterService.register(normalUser);

        Notification notification = new Notification(
                login.getId(),
                "Welcome to WellnessVision",
                "Congratulation!, Your registration successfully and now you can start your new journey with WellnessVision. " +
                        "Here you can participate physical and online events, make appointments, and read articles...",
                "Unread",
                LocalDateTime.now()
        );
        notificationService.createNewNotificationForAllUsers(notification);

        return "Registration successful!";

    }
}

