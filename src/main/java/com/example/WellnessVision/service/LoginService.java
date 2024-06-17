package com.example.WellnessVision.service;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public String login(String email, String password) {
        Login login = loginRepository.findByEmail(email).orElse(null);

        if (login != null && login.getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid email or password";
    }

    public void registerLogin(Login login) {
        loginRepository.save(login);
    }

    public Integer login_count(String user_type) {
        return loginRepository.login_count(user_type);
    }


}