package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.ChatMessage;
import com.example.WellnessVision.model.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    @Query(value = "SELECT * FROM chat_message WHERE hp_id = ?1 ORDER BY msg_time ASC", nativeQuery = true)
    Optional<List<ChatMessage>> getAllMessagesForHPAndEM(int HP_id);
}


