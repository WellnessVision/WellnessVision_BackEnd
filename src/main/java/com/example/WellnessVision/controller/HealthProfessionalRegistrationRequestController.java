package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.HealthProfessionalRegistrationRequestDto;
import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.service.FileUploadService;
import com.example.WellnessVision.service.HealthProfessionalRegistrationRequestService;
import com.example.WellnessVision.service.PasswordHashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")

public class HealthProfessionalRegistrationRequestController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private HealthProfessionalRegistrationRequestService healthProfessionalRegistrationRequestService;

    @PostMapping("/healthProfessionalRegistrationRequest")
    public void healthProfessionalRegistrationRequestSave(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address,
            @RequestParam("address2") String address2,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("province") String province,
            @RequestParam("zip") String zip,
            @RequestParam("email") String email,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("password") String password,
            @RequestParam("profession") String profession,
            @RequestParam("organization") String organization,
            @RequestParam("regNo") String regNo,
            @RequestParam("ownership") String ownership,
            @RequestParam("certificateImage") MultipartFile certificateImage,
            @RequestParam("otherVerificationPdf") MultipartFile otherVerificationPdf) throws IOException {
        String ProfilePictureLink = null;
        String certificateImageLink = null;
        String otherVerificationPdfLink = null;
        ResponseEntity<String> ProfilePictureLinkResponse = fileUploadService.uploadFile(profilePicture,email);
        if (ProfilePictureLinkResponse.getStatusCode() == HttpStatus.OK && ProfilePictureLinkResponse.getBody() != null) {
            ProfilePictureLink = ProfilePictureLinkResponse.getBody();
        }

        ResponseEntity<String> certificateImageLinkResponse = fileUploadService.uploadFile(certificateImage, email);
        if (certificateImageLinkResponse.getStatusCode() == HttpStatus.OK && certificateImageLinkResponse.getBody() != null) {
            certificateImageLink = certificateImageLinkResponse.getBody();
        }

        ResponseEntity<String> otherVerificationPdfLinkResponse = fileUploadService.uploadFile(otherVerificationPdf, email);
        if (otherVerificationPdfLinkResponse.getStatusCode() == HttpStatus.OK && otherVerificationPdfLinkResponse.getBody() != null) {
            otherVerificationPdfLink = otherVerificationPdfLinkResponse.getBody();
        }

        String hashedPassword = PasswordHashingService.hashPassword(password);

        HealthProfessionalRegistrationRequest healthProfessionalRegistrationRequest = new HealthProfessionalRegistrationRequest();
        healthProfessionalRegistrationRequest.setFirstName(firstName);
        healthProfessionalRegistrationRequest.setLastName(lastName);
        healthProfessionalRegistrationRequest.setAddress(address);
        healthProfessionalRegistrationRequest.setAddress2(address2);
        healthProfessionalRegistrationRequest.setCity(city);
        healthProfessionalRegistrationRequest.setDistrict(district);
        healthProfessionalRegistrationRequest.setProvince(province);
        healthProfessionalRegistrationRequest.setZip(zip);
        healthProfessionalRegistrationRequest.setEmail(email);
        healthProfessionalRegistrationRequest.setProfilePicture(ProfilePictureLink);
        healthProfessionalRegistrationRequest.setPassword(hashedPassword);
        healthProfessionalRegistrationRequest.setProfession(profession);
        healthProfessionalRegistrationRequest.setOrganization(organization);
        healthProfessionalRegistrationRequest.setRegNo(regNo);
        healthProfessionalRegistrationRequest.setOwnership(ownership);
        healthProfessionalRegistrationRequest.setCertificateImage(certificateImageLink);
        healthProfessionalRegistrationRequest.setOtherVerificationPdf(otherVerificationPdfLink);
        healthProfessionalRegistrationRequest.setRequestTime(LocalDateTime.now());

        healthProfessionalRegistrationRequestService.healthProfessionalRegistrationRequestSave(healthProfessionalRegistrationRequest);
    }

    @GetMapping("/ViewHealthProfessionalRegistrationRequest")
    public List<HealthProfessionalRegistrationRequest> viewHealthProfessionalRegistrationRequest(){
        return healthProfessionalRegistrationRequestService.viewHealthProfessionalRegistrationRequest();
    }

}
