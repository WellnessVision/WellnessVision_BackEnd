package com.example.WellnessVision.service;

import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.repository.AdminPrivilegeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AdminDashboardService {

    @Autowired
    private final  AdminPrivilegeUserRepository adminPrivilegeUserRepository;

    public AdminDashboardService(AdminPrivilegeUserRepository adminPrivilegeUserRepository) {
        this.adminPrivilegeUserRepository = adminPrivilegeUserRepository;
    }

    public Optional<AdminPrivilegeUser> adminDashboardProfileDetails(int adminId) throws IOException {
        return adminPrivilegeUserRepository.findById(adminId);
    }


}
