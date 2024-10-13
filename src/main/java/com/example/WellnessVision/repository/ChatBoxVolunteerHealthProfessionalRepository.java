package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.ChatBoxVolunteerHealthProfessional;
import com.example.WellnessVision.model.NormalUser;

import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.model.VolunteerPhysicalEventBooking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ChatBoxVolunteerHealthProfessionalRepository extends JpaRepository<ChatBoxVolunteerHealthProfessional, Integer>{

    @Query(value = "SELECT * FROM chat_box_volunteer_health_professional WHERE volunteer_id = ?1 AND event_id = ?2", nativeQuery = true)
    List<ChatBoxVolunteerHealthProfessional> getChatBoxForVolunteerAndPhysicalEventsForVolunteer(int VolunteerId, int eventId);

    @Query(value = "SELECT * FROM chat_box_volunteer_health_professional WHERE message_id = ?1", nativeQuery = true)
    List<ChatBoxVolunteerHealthProfessional> getOneChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int replyMessageId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE chat_box_volunteer_health_professional SET message = ?2, reading_state = ?3, edit_flag = ?4 WHERE message_id = ?1", nativeQuery = true)
    void updateChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int replyMessageId, String newMessage, String readingState, String editFlag);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chat_box_volunteer_health_professional WHERE message_id = ?1", nativeQuery = true)
    void deleteChatBoxMessageForVolunteerAndPhysicalEventsForVolunteer(int deleteMessageId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE chat_box_volunteer_health_professional SET reading_state = ?4 WHERE volunteer_id = ?1 AND event_id = ?2 AND sender = ?3", nativeQuery = true)
    void seenTheChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(int VolunteerId, int eventId, String sender, String readingState);

    @Query(value = "SELECT COUNT(*) AS messageCount FROM chat_box_volunteer_health_professional WHERE volunteer_id = ?1 AND event_id = ?2 AND sender = ?3 AND reading_state = ?4", nativeQuery = true)
    Integer getMessageCountChatBoxMessageForVolunteerAndPhysicalEventsForBothUsers(int VolunteerId, int eventId, String receiver, String readingState);

    @Query(value = "SELECT * FROM chat_box_volunteer_health_professional WHERE message_id = ?1", nativeQuery = true)
    ChatBoxVolunteerHealthProfessional getOneChatMessageDetailsForSendNotification(int messageId);

}
