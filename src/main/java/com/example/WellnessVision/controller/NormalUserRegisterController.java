package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.service.FileUploadService;
import com.example.WellnessVision.service.LoginService;
import com.example.WellnessVision.service.NormalUserRegisterService;
import com.example.WellnessVision.service.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

        NormalUser normalUser = new NormalUser(user_id, user_type, email, phone,district, city, address, address2,  firstName, lastName, preferences, province, hashPassword, profilePicLink);

        normalUserRegisterService.register(normalUser);
        return "Registration successful!";

    }
}

