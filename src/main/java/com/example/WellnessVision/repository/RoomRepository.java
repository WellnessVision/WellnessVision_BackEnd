package com.example.WellnessVision.repository;

import com.example.WellnessVision.model.Notification;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT * FROM room WHERE room_id = ?1", nativeQuery = true)
    Room getOneRoomDetailsUsingRoomId(String room_id);

    @Query(value = "SELECT * FROM room WHERE room_type = ?1 AND state = ?2", nativeQuery = true)
    List<Room> getAllAvailableRoomsForOneRoomTypeForHp(String roomType, String state);

}

