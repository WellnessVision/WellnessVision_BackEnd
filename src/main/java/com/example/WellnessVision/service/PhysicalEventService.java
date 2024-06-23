package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.model.PhysicalEventPayment;
import com.example.WellnessVision.repository.PhysicalEventOrderRepository;
import com.example.WellnessVision.repository.PhysicalEventPaymentRepository;
import com.example.WellnessVision.repository.PhysicalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalEventService {

    @Autowired
    private PhysicalEventRepository repository;
    private final PhysicalEventOrderRepository order_repository;

    private final PhysicalEventPaymentRepository physicalEventPaymentRepository;

    public PhysicalEventService(PhysicalEventOrderRepository orderRepository, PhysicalEventPaymentRepository physicalEventPaymentRepository) {
        order_repository = orderRepository;
        this.physicalEventPaymentRepository = physicalEventPaymentRepository;
    }

    public HallAvailability getEventsByCapacityAndStatus(int capacity, String hall_type, String Available,String Maintain,String Unavailable, LocalDate date, int start_time, int end_time, String event_title, String event_type, int duration, int ticket_price, String eventImage, String language, String event_description, int hpId) throws IOException {
        PhysicalEvent physicalEvent = new PhysicalEvent();
        physicalEvent.setEventTitle(event_title);
        physicalEvent.setFinalEventType(event_type);
        physicalEvent.setDate(date);
        physicalEvent.setStartTime(start_time);
        physicalEvent.setEndTime(end_time);
        physicalEvent.setFinalDuration(duration);
        physicalEvent.setCapacity(capacity);
        physicalEvent.setTicketPrice(ticket_price);
        physicalEvent.setEventImage(eventImage);
        physicalEvent.setLanguage(language);
        physicalEvent.setHp_id(hpId);
        physicalEvent.setEvent_description(event_description);
        physicalEvent.setHall_capacity(0);
        physicalEvent.setTotal_hall_charge(0);
        physicalEvent.setAdvance_percentage(0.00);
        physicalEvent.setAdvance_payment(0);
        physicalEvent.setPayment_id(0);
        List<Hall> halls = new ArrayList<>();
        halls =  repository.findByCapacityAndStatus(capacity, hall_type, Available, Maintain, Unavailable, date);
        for (Hall hall : halls) {
            int date_booking = repository.findByDate(hall.getHall_id(),date);
            if(date_booking == 0){
                physicalEvent.setHall_id(hall.getHall_id());
                PhysicalEvent savedEvent = order_repository.save(physicalEvent);
                Optional<Hall> bookHall = repository.findById(hall.getHall_id());
                Hall hall_2 = bookHall.get();
                HallAvailability hallAvailability = new HallAvailability();
                hallAvailability.setHall_id(hall_2.getHall_id());
                hallAvailability.setHall_type(hall_2.getHall_type());
                hallAvailability.setCapacity(hall_2.getCapacity());
                hallAvailability.setAdvance_percentage(hall_2.getAdvance_percentage());
                hallAvailability.setCharge(hall_2.getCharge());
                hallAvailability.setEvent_id(savedEvent.getEvent_id());
                return hallAvailability;
            }else {
                int time_booking = repository.findByTime(hall.getHall_id(), date, start_time, end_time);
                if(date_booking == time_booking){
                    physicalEvent.setHall_id(hall.getHall_id());
                    PhysicalEvent savedEvent = order_repository.save(physicalEvent);
                    Optional<Hall> bookHall = repository.findById(hall.getHall_id());
                    Hall hall_2 = bookHall.get();
                    HallAvailability hallAvailability = new HallAvailability();
                    hallAvailability.setHall_id(hall_2.getHall_id());
                    hallAvailability.setHall_type(hall_2.getHall_type());
                    hallAvailability.setCapacity(hall_2.getCapacity());
                    hallAvailability.setAdvance_percentage(hall_2.getAdvance_percentage());
                    hallAvailability.setCharge(hall_2.getCharge());
                    hallAvailability.setEvent_id(savedEvent.getEvent_id());
                    return hallAvailability;
                }
            }
        }

        return null;
    }

    public void cancelHallBooking(int event_id){
        order_repository.deleteById(event_id);
    }

    public void UploadEventImage(String eventImage, int eventId, int hall_capacity, int total_hall_charge, double advance_percentage, int advance_payment, int payment_id) throws IOException {
        repository.UploadEventImage(eventImage, eventId,  hall_capacity, total_hall_charge, advance_percentage, advance_payment, payment_id);
    }

    public Integer physicalEventPaymentSave(PhysicalEventPayment physicalEventPayment) {
        PhysicalEventPayment savedPhysicalEventPayment = physicalEventPaymentRepository.save(physicalEventPayment);
        return savedPhysicalEventPayment.getPayment_id();
    }

    public List<PhysicalEvent> getPhysicalEventForHP(int hp_id) {
        return order_repository.getPhysicalEventForHP(hp_id);
    }
}
