package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.RoomAlreadyBookedDatesDto;
import com.example.WellnessVision.dto.RoomMaintenanceDateSetStateDto;
import com.example.WellnessVision.model.AdminPrivilegeUser;
import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.Room;
import com.example.WellnessVision.repository.AdminPrivilegeUserRepository;
import com.example.WellnessVision.repository.HallRepository;
import com.example.WellnessVision.repository.PhysicalEventOrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventManagerService {

    AdminPrivilegeUserRepository adminPrivilegeUserRepository;
    HallRepository hallRepository;
    PhysicalEventOrderRepository physicalEventOrderRepository;

    EventManagerService(AdminPrivilegeUserRepository adminPrivilegeUserRepository, HallRepository hallRepository, PhysicalEventOrderRepository physicalEventOrderRepository){
        this.adminPrivilegeUserRepository = adminPrivilegeUserRepository;
        this.hallRepository = hallRepository;
        this.physicalEventOrderRepository = physicalEventOrderRepository;
    }

    public AdminPrivilegeUser getEventManagerDetailsForEventManager(int eventManagerId) throws IOException {
        return adminPrivilegeUserRepository.getAdminPrivilegeUserDetailsForAdminPrivilegeUsers(eventManagerId);
    }

    public List<Hall> getPhysicalEventHallsForEventManager() throws IOException {
        return hallRepository.getPhysicalEventHallsForEventManager();
    }

    public Hall getOnePhysicalEventHallDetailsForEventManager(String hallId) throws IOException {
        return hallRepository.getOnePhysicalEventHallDetailsForEventManager(hallId);
    }

    public List<PhysicalEvent> getPhysicalEventsOfOneHallForEventManager(String hallId) throws IOException {
        return physicalEventOrderRepository.getPhysicalEventsOfOneHallForEventManager(hallId, "Upcoming");
    }

    public List<RoomAlreadyBookedDatesDto> getAlreadyBookedDatesOfOneHallForEventManager(String hallId) throws IOException {
        List<Object[]> results =  physicalEventOrderRepository.getAlreadyBookedDatesOfOneHallForEventManager(hallId, LocalDate.now(), "Upcoming");
        return results.stream()
                .map(result -> {
                    java.sql.Date sqlDate = (java.sql.Date) result[0];
                    LocalDate localDate = sqlDate.toLocalDate();
                    return new RoomAlreadyBookedDatesDto(localDate);
                })
                .collect(Collectors.toList());
    }

    public RoomMaintenanceDateSetStateDto checkAndSetMaintenanceDateOfHallForEventManager(String hallId, LocalDate startDate, LocalDate endDate) throws IOException {
        int checkPhysicalEventBookingCount = physicalEventOrderRepository.checkAndSetMaintenanceDateOfHallForEventManager(hallId, startDate, endDate, "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(checkPhysicalEventBookingCount == 0){
            hallRepository.updateHallMaintenanceStartDateAndEndDateForEventManager(hallId, startDate, endDate);
            roomMaintenanceDateSetStateDto.setSetDateState("Updated");
        }else {
            roomMaintenanceDateSetStateDto.setSetDateState("InvalidDate");
        }

        roomMaintenanceDateSetStateDto.setStartDate(startDate);
        roomMaintenanceDateSetStateDto.setEndDate(endDate);

        return roomMaintenanceDateSetStateDto;

    }

    public RoomMaintenanceDateSetStateDto checkAndSetUnavailableDateOfHallForEventManager(String hallId, LocalDate unavailableDate) throws IOException {
        int checkPhysicalEventBookingCount = physicalEventOrderRepository.checkAndSetUnavailableDateOfHallForEventManager(hallId, unavailableDate, "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(checkPhysicalEventBookingCount == 0){
            hallRepository.updateHallUnavailableDateForEventManager(hallId, unavailableDate);
            roomMaintenanceDateSetStateDto.setSetDateState("Updated");
        }else {
            roomMaintenanceDateSetStateDto.setSetDateState("InvalidDate");
        }

        roomMaintenanceDateSetStateDto.setStartDate(unavailableDate);

        return roomMaintenanceDateSetStateDto;

    }

    public void clearHallSetDateOfHallForEventManager(String hallId) throws IOException {
        hallRepository.clearHallSetDateOfHallForEventManager(hallId);
    }

    public void updateHallChargeAndAdvancePercentageOfHallForEventManager(String hallId, int charge, double advancePercentage) throws IOException {
        hallRepository.updateHallChargeAndAdvancePercentageOfHallForEventManager(hallId, charge, advancePercentage);
    }

    public void changeHallStateOfHallForEventManager(String hallId, String hallState) throws IOException {
        hallRepository.changeHallStateOfHallForEventManager(hallId, hallState);
    }

    public RoomMaintenanceDateSetStateDto getLastBookDateOfHallForEventManager(String hallId) throws IOException {
        java.sql.Date LastBookDateInSQL = physicalEventOrderRepository.getLastBookDateOfHallForEventManager(hallId, LocalDate.now(), "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(LastBookDateInSQL == null){
            roomMaintenanceDateSetStateDto.setSetDateState("Yes");
        }else {
            LocalDate LastBookDate = LastBookDateInSQL.toLocalDate();
            roomMaintenanceDateSetStateDto.setSetDateState("No");
            roomMaintenanceDateSetStateDto.setStartDate(LastBookDate);
        }

        return roomMaintenanceDateSetStateDto;

    }

    public RoomMaintenanceDateSetStateDto CheckAndSetReduceHallCapacityOfHallForEventManager(String hallId, int newCapacity) throws IOException {
        Integer LastBookDateCount = physicalEventOrderRepository.CheckAndSetReduceHallCapacityOfHallForEventManager(hallId, LocalDate.now(), "Upcoming");
        RoomMaintenanceDateSetStateDto roomMaintenanceDateSetStateDto = new RoomMaintenanceDateSetStateDto();
        if(LastBookDateCount == 0){
            roomMaintenanceDateSetStateDto.setSetDateState("Updated");
            hallRepository.updateHallCapacityOfHallForEventManager(hallId, newCapacity);
        }else {
            roomMaintenanceDateSetStateDto.setSetDateState("InvalidCapacity");
        }

        return roomMaintenanceDateSetStateDto;

    }

    public void increasedHallCapacityOfHallForEventManager(String hallId, int newCapacity) throws IOException {
        hallRepository.updateHallCapacityOfHallForEventManager(hallId, newCapacity);
    }

    public void addNewPhysicalEventHallForEventManager(String hallId, String hallType, int charge, double advancePercentage, int capacity) throws IOException {

        Hall newHall = new Hall(
                hallId,
                hallType,
                capacity,
                charge,
                advancePercentage,
                "Available",
                null,
                null,
                null
        );

        hallRepository.save(newHall);

    }

}
