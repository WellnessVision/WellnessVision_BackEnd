package com.example.WellnessVision.repository;
import com.example.WellnessVision.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByEmail(String email);

    @Query(value = "SELECT COUNT(*) AS hp_user_count FROM login WHERE user_type = ?1", nativeQuery = true)
    Integer login_count(String user_type);

}
