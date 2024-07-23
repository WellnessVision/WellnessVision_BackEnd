package com.example.WellnessVision.controller;

import com.example.WellnessVision.dto.NormalUserFineAmountDto;
import com.example.WellnessVision.model.NormalUser;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventBooking;
import com.example.WellnessVision.service.NormalUserPhysicalEventService;
import com.example.WellnessVision.service.NormalUserRegisterService;
import com.example.WellnessVision.service.PhysicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")

public class NormalUserPhysicalEventController {

    @Autowired
    private NormalUserRegisterService normalUserRegisterService;

    @Autowired
    private NormalUserPhysicalEventService normalUserPhysicalEventService;

    @Autowired
    private PhysicalEventService physicalEventService;

    @GetMapping("/getNormalUserDetails")
    public NormalUser getNormalUserDetails(@RequestParam("userId") int user_id){

        return normalUserRegisterService.getUserDetails(user_id);
    }

    @GetMapping("/getUpcomingPhysicalEventsForUsers")
    public List<PhysicalEvent> getUpcomingPhysicalEventsForUsers(@RequestParam("eventState") String eventState) {
        return (List<PhysicalEvent>) normalUserPhysicalEventService.getUpcomingPhysicalEventsForUsers(eventState);
    }

    @GetMapping("/checkBookingStateOfOnePhysicalEventDetailForUser")
    public String checkBookingStateOfOnePhysicalEventDetailForUser(@RequestParam("eventId") int event_id, @RequestParam("userId") int userId) {
        int BookingState =  normalUserPhysicalEventService.checkBookingStateOfOnePhysicalEventDetailForUser(event_id, userId);
        if(BookingState == 1){
            return "Booked";
        }else {
            return "NotBooked";
        }
    }

//    @GetMapping("/getBookedPhysicalEventsByUserId")
//    public List<PhysicalEvent> getAllBookedPhysicalEventsByUserIdForUser(@RequestParam("userId") int user_id) {
//        return (List<PhysicalEvent>) normalUserPhysicalEventService.getAllBookedPhysicalEventsByUserId(user_id);
//    }

    @PutMapping("/userPhysicalEventTemporaryBooking")
    public String userPhysicalEventTemporaryBooking(@RequestParam("eventId") int eventId)
    {
        PhysicalEvent checkPhysicalEvent = physicalEventService.getOnePhysicalEventDetailForHP(eventId);
        if(checkPhysicalEvent.getHall_capacity() > checkPhysicalEvent.getTicketBookingCount()){
            normalUserPhysicalEventService.temporaryBookingPhysicalEventTicket(eventId);
            return "Booked";
        }
        return "NotBooked";
    }

    @PutMapping("/userPhysicalEventTemporaryBookingCancel")
    public void userPhysicalEventTemporaryBookingCancel(@RequestParam("eventId") int eventId)
    {
        normalUserPhysicalEventService.userPhysicalEventTemporaryBookingCancel(eventId);

    }

    @PutMapping("/userPhysicalEventBooking")
    public void userPhysicalEventBooking(@RequestParam("eventId") int event_id, @RequestParam("userId") int user_id, @RequestParam("ticketPrice") int ticketPrice, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        normalUserPhysicalEventService.userPhysicalEventBooking(event_id, user_id, ticketPrice, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/getBookedUpcomingPhysicalEventsForUsers")
    public List<PhysicalEvent> getBookedUpcomingPhysicalEventsForUsers(@RequestParam("userId") int userId,  @RequestParam("bookingState") String bookingState, @RequestParam("eventState") String eventState) {
        return normalUserPhysicalEventService.getBookedUpcomingPhysicalEventsForUsers(userId, bookingState, eventState);
    }

    @GetMapping("/getBookingDetailsBookedUpcomingPhysicalEventsForUsers")
    public PhysicalEventBooking getBookingDetailsBookedUpcomingPhysicalEventsForUsers(@RequestParam("userId") int userId, @RequestParam("eventId") int eventId) {
        return normalUserPhysicalEventService.getBookingDetailsBookedUpcomingPhysicalEventsForUsers(userId, eventId, "Booking");
    }

    @PutMapping("/updateOnePhysicalEventMoneyReceiptsDetailsForNU")
    public void updateOnePhysicalEventMoneyReceiptsDetailsForNU(@RequestParam("bookingId") int booking_id, @RequestParam("accountNumber") String account_number, @RequestParam("accountOwnerName") String account_owner_name, @RequestParam("branchName") String branch_name, @RequestParam("bankName") String bank_name)
    {
        normalUserPhysicalEventService.updateOnePhysicalEventMoneyReceiptsDetailsForNU(booking_id, account_number, account_owner_name, branch_name, bank_name);
    }

    @GetMapping("/getFineAmountForNU")
    public NormalUserFineAmountDto getFineAmountForNU(@RequestParam("eventId") int eventId) {
        return normalUserPhysicalEventService.getFineAmountForNU(eventId);
    }

    @PutMapping("/deletePhysicalEventBookingForNU")
    public void deletePhysicalEventBookingForNU(@RequestParam("eventId") int event_id, @RequestParam("userId") int user_id,  @RequestParam("bookingId") int booking_id, @RequestParam("fineAmount") int fineAmount, @RequestParam("depositAmount") int depositAmount, @RequestParam("twoDaysBeforeState") String twoDaysBeforeState) {
        normalUserPhysicalEventService.deletePhysicalEventBookingForNU(event_id, user_id, booking_id, fineAmount, depositAmount, twoDaysBeforeState);
    }


}
