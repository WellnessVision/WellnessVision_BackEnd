package com.example.WellnessVision.service;

import com.example.WellnessVision.model.Login;
import com.example.WellnessVision.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Optional<Login> login(String email, String password) {
        Optional<Login> optionalLogin = loginRepository.findByEmail(email);
        System.out.println(PasswordHashingService.hashPassword("1Aa#1111"));

        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            boolean passwordState = PasswordHashingService.checkPassword(password, login.getPassword());

            if (passwordState) {
                return Optional.of(login);
            }
        }

        return Optional.empty();
    }

    public void registerLogin(Login login) {
        loginRepository.save(login);
    }

//    public Integer login_count(String user_type) {
//        return loginRepository.login_count(user_type);
//    }


}