package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.NormalUser;

import com.example.WellnessVision.model.PhysicalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NormalUserRegisterRepository extends JpaRepository<NormalUser, Integer>{
    Optional<NormalUser> findByEmail(String email);

    @Query(value = "SELECT * FROM normal_user WHERE user_id = ?1", nativeQuery = true)
    NormalUser getOneUserDetails(int user_id);

    Optional<NormalUser> findUserDetailsByUser_id(int user_id);

}
