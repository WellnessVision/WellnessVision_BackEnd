package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.model.VolunteerWorkCollection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VolunteerWorkCollectionRepository extends JpaRepository<VolunteerWorkCollection, Integer> {

    @Query(value = "SELECT * FROM volunteer_work_collection WHERE volunteer_id = ?1 ORDER BY modified_time DESC", nativeQuery = true)
    List<VolunteerWorkCollection> getOneVolunteerWorkCollectionForVolunteer(int volunteerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE volunteer_work_collection SET subject = ?2, description = ?3, modified_time = ?4 WHERE work_collection_id = ?1", nativeQuery = true)
    void editTextInWorkCollectionForVolunteer(int workCollectionId, String subject, String description, LocalDateTime modifiedTime);

    @Modifying
    @Transactional
    @Query(value = "UPDATE volunteer_work_collection SET attachment = ?2, modified_time = ?3 WHERE work_collection_id = ?1", nativeQuery = true)
    void editAttachmentInWorkCollectionForVolunteer(int workCollectionId, String attachment, LocalDateTime modifiedTime);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM volunteer_work_collection WHERE work_collection_id = ?1", nativeQuery = true)
    void deleteWorkInWorkCollectionForVolunteer(int deletedWorkCollectionId);

}
