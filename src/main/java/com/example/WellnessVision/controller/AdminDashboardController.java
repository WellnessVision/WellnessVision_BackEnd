package com.example.WellnessVision.controller;

import com.example.WellnessVision.model.*;
import com.example.WellnessVision.service.AdminDashboardService;
import com.example.WellnessVision.service.FileUploadService;
import com.example.WellnessVision.service.LoginService;
import com.example.WellnessVision.service.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/adminDashboardProfileDetails")
    public Optional<AdminPrivilegeUser> healthProfessionalDashboardProfileDetails(@RequestParam("adminId") int adminId) throws IOException {
        return adminDashboardService.adminDashboardProfileDetails(adminId);
    }

    @GetMapping("/adminViewAllUsersForAdmin")
    public List<NormalUser> adminViewAllUsersForAdmin() throws IOException {
        return adminDashboardService.adminViewAllUsersForAdmin();
    }
    @GetMapping("/adminViewOneUserForAdmin")
    public NormalUser adminViewOneUserForAdmin(@RequestParam("userId") int userId) throws IOException {
        return adminDashboardService.adminViewOneUserForAdmin(userId);
    }

    @GetMapping("/adminViewAllVolunteersForAdmin")
    public List<Volunteer> adminViewAllVolunteersForAdmin() throws IOException {
        return adminDashboardService.adminViewAllVolunteersForAdmin();
    }

    @GetMapping("/adminViewOneVolunteersForAdmin")
    public Volunteer adminViewOneVolunteersForAdmin(@RequestParam("volunteerId") int volunteerId) throws IOException {
        return adminDashboardService.adminViewOneVolunteersForAdmin(volunteerId);
    }

    @GetMapping("/adminViewAllSystemPrivilegeUserForAdmin")
    public List<AdminPrivilegeUser> adminViewAllSystemPrivilegeUserForAdmin(@RequestParam("searchNameCode") String searchNameCode, @RequestParam("searchTypeCode") String searchTypeCode) throws IOException {
        return adminDashboardService.adminViewAllSystemPrivilegeUserForAdmin(searchNameCode, searchTypeCode);
    }
    @GetMapping("/adminViewOneSystemPrivilegeUserForAdmin")
    public AdminPrivilegeUser adminViewOneSystemPrivilegeUserForAdmin(@RequestParam("systemPrivilegeUserId") int systemPrivilegeUserId) throws IOException {
        return adminDashboardService.adminViewOneSystemPrivilegeUserForAdmin(systemPrivilegeUserId);
    }

    @PostMapping("/AddNewAdminPrivilegeUserRegistrationForAdmin")
    public Integer AddNewAdminPrivilegeUserRegistrationForAdmin(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address,
            @RequestParam("address2") String address2,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("province") String province,
            @RequestParam("zip") String zip,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("adminType") String adminType,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("password") String password) throws IOException {

        String ProfilePictureLink = null;

        ResponseEntity<String> ProfilePictureLinkResponse = fileUploadService.uploadFile(profilePicture,email);
        if (ProfilePictureLinkResponse.getStatusCode() == HttpStatus.OK && ProfilePictureLinkResponse.getBody() != null) {
            ProfilePictureLink = ProfilePictureLinkResponse.getBody();
        }

        String hashedPassword = PasswordHashingService.hashPassword(password);

        Login newLogin = new Login();
        newLogin.setUser_type(adminType);
        newLogin.setEmail(email);
        newLogin.setPassword(hashedPassword);

        int newRegisteredID = loginService.adminRegisterLogin(newLogin);

        AdminPrivilegeUser newAdminPrivilegeUser = new AdminPrivilegeUser(
                newRegisteredID,
                adminType,
                firstName,
                lastName,
                email,
                hashedPassword,
                phoneNumber,
                ProfilePictureLink,
                address,
                address2,
                city,
                district,
                province,
                zip
        );

        adminDashboardService.AddNewAdminPrivilegeUserRegistrationForAdmin(newAdminPrivilegeUser);

        return newRegisteredID;

    }

}
