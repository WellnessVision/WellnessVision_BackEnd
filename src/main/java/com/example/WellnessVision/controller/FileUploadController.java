package com.example.WellnessVision.controller;

import com.example.WellnessVision.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:5173")


public class FileUploadController {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final S3Service s3Service;

    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String userEmail = "ruchithsathnidu123@gmail.com";
            String key = s3Service.uploadFile(file, userEmail);
            return ResponseEntity.ok("https://" + bucketName + ".s3.ap-south-1.amazonaws.com/" + key);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}

