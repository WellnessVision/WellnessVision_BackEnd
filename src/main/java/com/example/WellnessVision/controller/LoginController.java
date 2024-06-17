package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public String register(@RequestBody Login login) {
        loginService.registerLogin(login);
        return "Registration successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        System.out.println("Login endpoint called with email: " + login.getEmail());
        return loginService.login(login.getEmail(), login.getPassword());
    }

    @GetMapping("/logincount")
    public Integer login_count(@RequestParam String user_type) {
        return loginService.login_count(user_type);
    }
}
