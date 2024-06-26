package com.example.WellnessVision.service;


import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.repository.NormalUserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalUserRegisterService {

    @Autowired
    private NormalUserRegisterRepository normalUserRegisterRepository;

    public void register(NormalUser normalUser) {
        normalUserRegisterRepository.save(normalUser);
    }
}
