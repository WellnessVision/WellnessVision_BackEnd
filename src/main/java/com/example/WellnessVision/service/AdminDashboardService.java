package com.example.WellnessVision.service;

import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.repository.AdminPrivilegeUserRepository;
import com.example.WellnessVision.repository.NormalUserRegisterRepository;
import com.example.WellnessVision.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDashboardService {

    @Autowired
    private final  AdminPrivilegeUserRepository adminPrivilegeUserRepository;

    @Autowired
    NormalUserRegisterRepository normalUserRegisterRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    public AdminDashboardService(AdminPrivilegeUserRepository adminPrivilegeUserRepository) {
        this.adminPrivilegeUserRepository = adminPrivilegeUserRepository;
    }

    public Optional<AdminPrivilegeUser> adminDashboardProfileDetails(int adminId) throws IOException {
        return adminPrivilegeUserRepository.findById(adminId);
    }

    public List<NormalUser> adminViewAllUsersForAdmin() throws IOException {
        return normalUserRegisterRepository.adminViewAllUsersForAdmin();
    }

    public NormalUser adminViewOneUserForAdmin(int userId) throws IOException {
        return normalUserRegisterRepository.getOneUserDetails(userId);
    }

    public List<Volunteer> adminViewAllVolunteersForAdmin() throws IOException {
        return volunteerRepository.adminViewAllVolunteersForAdmin();
    }

    public Volunteer adminViewOneVolunteersForAdmin(int volunteerId) throws IOException {
        return volunteerRepository.getVolunteerDetailsByVolunteerId(volunteerId);
    }

    public List<AdminPrivilegeUser> adminViewAllSystemPrivilegeUserForAdmin(String searchNameCode, String searchTypeCode) throws IOException {
        String modifiedSearchNameCode = searchNameCode + "%";
        String modifiedSearchTypeCode = searchTypeCode + "%";
        return adminPrivilegeUserRepository.adminViewAllSystemPrivilegeUserForAdmin(modifiedSearchNameCode, modifiedSearchTypeCode);
    }

    public AdminPrivilegeUser adminViewOneSystemPrivilegeUserForAdmin(int adminPrivilegeUserId) throws IOException {
        return adminPrivilegeUserRepository.getAdminPrivilegeUserDetailsForAdminPrivilegeUsers(adminPrivilegeUserId);
    }

    public void AddNewAdminPrivilegeUserRegistrationForAdmin(AdminPrivilegeUser newAdminPrivilegeUser) throws IOException {
         adminPrivilegeUserRepository.save(newAdminPrivilegeUser);
    }



}
