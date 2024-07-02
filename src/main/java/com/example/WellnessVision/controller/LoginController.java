package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class LoginController {

    @Autowired
    private LoginService loginService;

//    @PostMapping("/register")
//    public String register(@RequestBody Login login) {
//        loginService.registerLogin(login);
//        return "Registration successful!";
//    }

    @GetMapping("/login")
    public Optional<Login> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return loginService.login(email,password);
    }

//    @GetMapping("/logincount")
//    public Integer login_count(@RequestParam String user_type) {
//        return loginService.login_count(user_type);
//    }
}
