package com.example.WellnessVision.service;

import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.repository.HealthProfessionalRegistrationRequestRepository;
import com.example.WellnessVision.repository.HealthProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HealthProfessionalRegistrationRequestService {

    @Autowired
    private final HealthProfessionalRegistrationRequestRepository healthProfessionalRegistrationRequestRepository;

    @Autowired
    private final HealthProfessionalRepository healthProfessionalRepository;

    public HealthProfessionalRegistrationRequestService(HealthProfessionalRegistrationRequestRepository healthProfessionalRegistrationRequestRepository, HealthProfessionalRepository healthProfessionalRepository) {
        this.healthProfessionalRegistrationRequestRepository = healthProfessionalRegistrationRequestRepository;
        this.healthProfessionalRepository = healthProfessionalRepository;
    }

    public void healthProfessionalRegistrationRequestSave(HealthProfessionalRegistrationRequest healthProfessionalRegistrationRequest) throws IOException {
        healthProfessionalRegistrationRequestRepository.save(healthProfessionalRegistrationRequest);
    }

    public List<HealthProfessionalRegistrationRequest> viewHealthProfessionalRegistrationRequest(){
        return healthProfessionalRegistrationRequestRepository.viewHealthProfessionalRegistrationRequest();
    }

    public HealthProfessionalRegistrationRequest viewOneHealthProfessionalRegistrationRequest(int requestId){
        return healthProfessionalRegistrationRequestRepository.viewOneHealthProfessionalRegistrationRequest(requestId);
    }

    public void healthProfessionalSave(HealthProfessional healthProfessional) throws IOException {
        healthProfessionalRepository.save(healthProfessional);
    }

    public void deleteHealthProfessionalRegistrationRequest(int requestId){
        healthProfessionalRegistrationRequestRepository.deleteById(requestId);
    }

}
