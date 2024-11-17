package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.RoomAlreadyBookedDatesDto;
import com.example.WellnessVision.dto.RoomMaintenanceDateSetStateDto;
import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.model.AppointmentSchedule;
import com.example.WellnessVision.model.Room;
import com.example.WellnessVision.repository.RoomRepository;
import com.example.WellnessVision.service.AppointmentManagerService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class AppointmentManagerController {

    AppointmentManagerService appointmentManagerService;

    AppointmentManagerController(AppointmentManagerService appointmentManagerService){
        this.appointmentManagerService = appointmentManagerService;
    }

    @GetMapping("/getAppointmentManagerDetailsForAppointmentManager")
    public AdminPrivilegeUser getAppointmentManagerDetailsForAppointmentManager(@RequestParam("appointmentManagerId") int appointmentManagerId) throws IOException {
        return appointmentManagerService.getAppointmentManagerDetailsForAppointmentManager(appointmentManagerId);
    }

    @GetMapping("/getAppointmentRoomsForAppointmentManager")
    public List<Room> getAppointmentRoomsForAppointmentManager() throws IOException {
        return appointmentManagerService.getAppointmentRoomsForAppointmentManager();
    }

    @GetMapping("/getOneAppointmentRoomsDetailsForAppointmentManager")
    public Room getOneAppointmentRoomsDetailsForAppointmentManager(@RequestParam("roomId") String roomId) throws IOException {
        return appointmentManagerService.getOneAppointmentRoomsDetailsForAppointmentManager(roomId);
    }

    @GetMapping("/getAppointmentSchedulesOfRoomForAppointmentManager")
    public List<AppointmentSchedule> getAppointmentSchedulesOfRoomForAppointmentManager(@RequestParam("roomId") String roomId) throws IOException {
        return appointmentManagerService.getAppointmentSchedulesOfRoomForAppointmentManager(roomId);
    }

    @GetMapping("/getAlreadyBookedDatesOfOneRoomForAppointmentManager")
    public List<RoomAlreadyBookedDatesDto> getAlreadyBookedDatesOfOneRoomForAppointmentManager(@RequestParam("roomId") String roomId) throws IOException {
        return appointmentManagerService.getAlreadyBookedDatesOfOneRoomForAppointmentManager(roomId);
    }

    @GetMapping("/checkAndSetMaintenanceDateOfRoomsForAppointmentManager")
    public RoomMaintenanceDateSetStateDto checkAndSetMaintenanceDateOfRoomsForAppointmentManager(@RequestParam("roomId") String roomId, @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) throws IOException {
        return appointmentManagerService.checkAndSetMaintenanceDateOfRoomsForAppointmentManager(roomId, startDate, endDate);
    }

    @GetMapping("/checkAndSetUnavailableDateOfRoomsForAppointmentManager")
    public RoomMaintenanceDateSetStateDto checkAndSetUnavailableDateOfRoomsForAppointmentManager(@RequestParam("roomId") String roomId, @RequestParam("unavailableDate") LocalDate unavailableDate) throws IOException {
        return appointmentManagerService.checkAndSetUnavailableDateOfRoomsForAppointmentManager(roomId, unavailableDate);
    }

    @PutMapping("/clearRoomSetDateOfRoomsForAppointmentManager")
    public void clearRoomSetDateOfRoomsForAppointmentManager(@RequestParam("roomId") String roomId) throws IOException {
          appointmentManagerService.clearRoomSetDateOfRoomsForAppointmentManager(roomId);
    }

    @PutMapping("/updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager")
    public void updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager(@RequestParam("roomId") String roomId, @RequestParam("charge") int charge, @RequestParam("advancePercentage") double advancePercentage) throws IOException {
        appointmentManagerService.updateRoomChargeAndAdvancePercentageOfRoomsForAppointmentManager(roomId, charge, advancePercentage);
    }

    @PutMapping("/changeRoomStateOfRoomsForAppointmentManager")
    public void changeRoomStateOfRoomsForAppointmentManager(@RequestParam("roomId") String roomId, @RequestParam("roomState") String roomState) throws IOException {
        appointmentManagerService.changeRoomStateOfRoomsForAppointmentManager(roomId, roomState);
    }

    @PostMapping("/addNewAppointmentRoomForAppointmentManager")
    public void addNewAppointmentRoomForAppointmentManager(@RequestParam("roomId") String roomId, @RequestParam("roomType") String roomType, @RequestParam("charge") int charge, @RequestParam("advancePercentage") double advancePercentage) throws IOException {
        appointmentManagerService.addNewAppointmentRoomForAppointmentManager(roomId, roomType, charge, advancePercentage);
    }

}
