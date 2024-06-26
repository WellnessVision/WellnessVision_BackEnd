package com.example.WellnessVision.service;

import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.repository.HealthProfessionalRegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HealthProfessionalRegistrationRequestService {

    @Autowired
    private final HealthProfessionalRegistrationRequestRepository healthProfessionalRegistrationRequestRepository;

    public HealthProfessionalRegistrationRequestService(HealthProfessionalRegistrationRequestRepository healthProfessionalRegistrationRequestRepository) {
        this.healthProfessionalRegistrationRequestRepository = healthProfessionalRegistrationRequestRepository;
    }

    public void healthProfessionalRegistrationRequestSave(HealthProfessionalRegistrationRequest healthProfessionalRegistrationRequest) throws IOException {
        healthProfessionalRegistrationRequestRepository.save(healthProfessionalRegistrationRequest);
    }

    public List<HealthProfessionalRegistrationRequest> viewHealthProfessionalRegistrationRequest(){
        return healthProfessionalRegistrationRequestRepository.viewHealthProfessionalRegistrationRequest();
    }

}
