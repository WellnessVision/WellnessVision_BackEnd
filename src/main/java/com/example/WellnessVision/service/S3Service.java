package com.example.WellnessVision.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String userEmail) throws IOException {
        String key = userEmail.split("@")[0] + "-" + System.currentTimeMillis() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

        if (putObjectResponse.sdkHttpResponse().isSuccessful()) {
            return key;
        } else {
            throw new RuntimeException("Failed to upload file to S3");
        }
    }
}

