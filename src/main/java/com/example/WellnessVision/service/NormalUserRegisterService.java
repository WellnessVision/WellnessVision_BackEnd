package com.example.WellnessVision.service;


import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.repository.NormalUserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NormalUserRegisterService {

    @Autowired
    private NormalUserRegisterRepository normalUserRegisterRepository;

    public void register(NormalUser normalUser) {
        normalUserRegisterRepository.save(normalUser);
    }

    public Optional<NormalUser> getUserDetails(int user_id){
        return normalUserRegisterRepository.findUserDetailsByUser_id(user_id);
    }
}
