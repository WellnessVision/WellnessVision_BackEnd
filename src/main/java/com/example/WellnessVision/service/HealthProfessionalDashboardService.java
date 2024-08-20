package com.example.WellnessVision.service;

import com.example.WellnessVision.model.HealthProfessional;
import com.example.WellnessVision.repository.HealthProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HealthProfessionalDashboardService {

    @Autowired
    private final HealthProfessionalRepository healthProfessionalRepository;

    public HealthProfessionalDashboardService(HealthProfessionalRepository healthProfessionalRepository) {
        this.healthProfessionalRepository = healthProfessionalRepository;
    }

    public Optional<HealthProfessional> healthProfessionalDashboardProfileDetails(int hpId) throws IOException {
        return healthProfessionalRepository.findById(hpId);
    }

    public Optional<List<HealthProfessional>> getAllHealthProfessionals() throws IOException {
        return Optional.ofNullable(healthProfessionalRepository.findAllHealthProfessionals());
    }
}
