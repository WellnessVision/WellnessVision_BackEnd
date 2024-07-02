package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NormalUserRegisterRepository extends JpaRepository<NormalUser, Integer>{
    Optional<NormalUser> findByEmail(String email);

}
