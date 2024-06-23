package com.example.WellnessVision.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public class FileUploadService {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final S3Service s3Service;


    public FileUploadService(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public ResponseEntity<String> uploadFile(MultipartFile file, String userEmail) {
        try {
            String key = s3Service.uploadFile(file, userEmail);
            return ResponseEntity.ok("https://" + bucketName + ".s3.ap-south-1.amazonaws.com/" + key);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}

