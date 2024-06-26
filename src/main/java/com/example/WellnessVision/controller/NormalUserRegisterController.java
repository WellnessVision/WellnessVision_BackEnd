package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.service.LoginService;
import com.example.WellnessVision.service.NormalUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @PostMapping("/userRegister")
    public String userRegister(
            @RequestParam("user_type") String user_type,
            @RequestParam("email") String email,
            @RequestParam("dob") LocalDate dob,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("preferences") String preferences,
            @RequestParam("province") String province,
            @RequestParam("password") String password,
            @RequestParam("profilePic") MultipartFile profilePic
            )
    {

        Login login = new Login();


        login.setUser_type(user_type);
        login.setEmail(email);
        login.setPassword(password);

        loginService.registerLogin(login);
        int user_id = login.getId();



        NormalUser normalUser = new NormalUser(user_id, user_type, email, dob, firstName, lastName, preferences, province, password);

        normalUserRegisterService.register(normalUser);
        return "Registration successful!";

    }
}

