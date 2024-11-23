package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT * FROM room WHERE room_id = ?1", nativeQuery = true)
    Room getOneRoomDetailsUsingRoomId(String room_id);

    @Query(value = "SELECT * FROM room WHERE room_type = ?1 AND state = ?2", nativeQuery = true)
    List<Room> getAllAvailableRoomsForOneRoomTypeForHp(String roomType, String state);

    @Query(value = "SELECT * FROM room", nativeQuery = true)
    List<Room> getAppointmentRoomsForAppointmentManager();

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET maintain_start_date = ?2, maintain_end_date = ?3 WHERE room_id = ?1", nativeQuery = true)
    void updateRoomMaintenanceStartDateAndEndDateForAppointmentManager(String roomId, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET unavailable_date = ?2 WHERE room_id = ?1", nativeQuery = true)
    void updateRoomUnavailableDateForAppointmentManager(String roomId, LocalDate unavailableDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET maintain_start_date = null, maintain_end_date = null, unavailable_date = null WHERE room_id = ?1", nativeQuery = true)
    void clearRoomSetDateOfRoomsForAppointmentManager(String roomId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET charge = ?2, advance_percentage = ?3 WHERE room_id = ?1", nativeQuery = true)
    void updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager(String roomId, int charge, double advancePercentage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET state = ?2 WHERE room_id = ?1", nativeQuery = true)
    void changeRoomStateOfRoomsForAppointmentManager(String roomId, String roomState);

}

