package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.RoomAlreadyBookedDatesDto;
import com.example.WellnessVision.dto.RoomMaintenanceDateSetStateDto;
import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.model.AppointmentSchedule;
import com.example.WellnessVision.model.Room;
import com.example.WellnessVision.repository.AdminPrivilegeUserRepository;
import com.example.WellnessVision.repository.HealthProfessionalAppointmentScheduleRepository;
import com.example.WellnessVision.repository.RoomRepository;
import com.example.WellnessVision.repository.UserAppointmentBookingRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManagerService {

    AdminPrivilegeUserRepository adminPrivilegeUserRepository;
    RoomRepository roomRepository;
    HealthProfessionalAppointmentScheduleRepository healthProfessionalAppointmentScheduleRepository;
    UserAppointmentBookingRepository userAppointmentBookingRepository;

    AppointmentManagerService(AdminPrivilegeUserRepository adminPrivilegeUserRepository, RoomRepository roomRepository, HealthProfessionalAppointmentScheduleRepository healthProfessionalAppointmentScheduleRepository, UserAppointmentBookingRepository userAppointmentBookingRepository){
        this.adminPrivilegeUserRepository = adminPrivilegeUserRepository;
        this.roomRepository = roomRepository;
        this.healthProfessionalAppointmentScheduleRepository = healthProfessionalAppointmentScheduleRepository;
        this.userAppointmentBookingRepository = userAppointmentBookingRepository;
    }

    public AdminPrivilegeUser getAppointmentManagerDetailsForAppointmentManager(int appointmentManagerId) throws IOException {
        return adminPrivilegeUserRepository.getAdminPrivilegeUserDetailsForAdminPrivilegeUsers(appointmentManagerId);
    }

    public List<Room> getAppointmentRoomsForAppointmentManager() throws IOException {
        return roomRepository.getAppointmentRoomsForAppointmentManager();
    }

    public Room getOneAppointmentRoomsDetailsForAppointmentManager(String roomId) throws IOException {
        return roomRepository.getOneRoomDetailsUsingRoomId(roomId);
    }

    public List<AppointmentSchedule> getAppointmentSchedulesOfRoomForAppointmentManager(String roomId) throws IOException {
        return healthProfessionalAppointmentScheduleRepository.getAppointmentSchedulesOfRoomForAppointmentManager(roomId);
    }

    public List<RoomAlreadyBookedDatesDto> getAlreadyBookedDatesOfOneRoomForAppointmentManager(String roomId) throws IOException {
        List<Object[]> results =  userAppointmentBookingRepository.getAlreadyBookedDatesOfOneRoomForAppointmentManager(roomId, LocalDate.now(), "Booked", "Upcoming");
        return results.stream()
                .map(result -> {
                    java.sql.Date sqlDate = (java.sql.Date) result[0];
                    LocalDate localDate = sqlDate.toLocalDate();
                    return new RoomAlreadyBookedDatesDto(localDate);
                })
                .collect(Collectors.toList());
    }

    public RoomMaintenanceDateSetStateDto checkAndSetMaintenanceDateOfRoomsForAppointmentManager(String roomId, LocalDate startDate, LocalDate endDate) throws IOException {
        int checkAppointmentBookingCount = userAppointmentBookingRepository.checkAndSetMaintenanceDateOfRoomsForAppointmentManager(roomId, startDate, endDate, "Booked", "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(checkAppointmentBookingCount == 0){
            roomRepository.updateRoomMaintenanceStartDateAndEndDateForAppointmentManager(roomId, startDate, endDate);
            roomMaintenanceDateSetStateDto.setSetDateState("Updated");
        }else {
            roomMaintenanceDateSetStateDto.setSetDateState("InvalidDate");
        }

        roomMaintenanceDateSetStateDto.setStartDate(startDate);
        roomMaintenanceDateSetStateDto.setEndDate(endDate);

        return roomMaintenanceDateSetStateDto;

    }

    public RoomMaintenanceDateSetStateDto checkAndSetUnavailableDateOfRoomsForAppointmentManager(String roomId, LocalDate unavailableDate) throws IOException {
        int checkAppointmentBookingCount = userAppointmentBookingRepository.checkAndSetUnavailableDateOfRoomsForAppointmentManager(roomId, unavailableDate, "Booked", "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(checkAppointmentBookingCount == 0){
            roomRepository.updateRoomUnavailableDateForAppointmentManager(roomId, unavailableDate);
            roomMaintenanceDateSetStateDto.setSetDateState("Updated");
        }else {
            roomMaintenanceDateSetStateDto.setSetDateState("InvalidDate");
        }

        roomMaintenanceDateSetStateDto.setStartDate(unavailableDate);

        return roomMaintenanceDateSetStateDto;

    }
    public void clearRoomSetDateOfRoomsForAppointmentManager(String roomId) throws IOException {
          roomRepository.clearRoomSetDateOfRoomsForAppointmentManager(roomId);
    }

    public void updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager(String roomId, int charge, double advancePercentage) throws IOException {
        roomRepository.updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager(roomId, charge, advancePercentage);
    }

    public void changeRoomStateOfRoomsForAppointmentManager(String roomId, String roomState) throws IOException {
        roomRepository.changeRoomStateOfRoomsForAppointmentManager(roomId, roomState);
    }

    public void addNewAppointmentRoomForAppointmentManager(String roomId, String roomType, int charge, double advancePercentage) throws IOException {
        Room newRoom = new Room(
                roomId,
                roomType,
                charge,
                advancePercentage,
                "Available",
                null,
                null,
                null
        );

        roomRepository.save(newRoom);

    }

}
