package com.example.WellnessVision.service;

import com.example.WellnessVision.dto.HallAvailability;
import com.example.WellnessVision.model.Hall;
import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.repository.PhysicalEventOrderRepository;
import com.example.WellnessVision.repository.PhysicalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhysicalEventService {

    @Autowired
    private PhysicalEventRepository repository;
    private final PhysicalEventOrderRepository order_repository;

    public PhysicalEventService(PhysicalEventOrderRepository orderRepository) {
        order_repository = orderRepository;
    }

    public HallAvailability getEventsByCapacityAndStatus(int capacity, String hall_type, String status, LocalDate date, int start_time, int end_time, String event_title, String event_type, int duration, int ticket_price) {
        PhysicalEvent physicalEvent = new PhysicalEvent();
        physicalEvent.setEventTitle(event_title);
        physicalEvent.setFinalEventType(event_type);
        physicalEvent.setDate(date);
        physicalEvent.setStartTime(start_time);
        physicalEvent.setEndTime(end_time);
        physicalEvent.setFinalDuration(duration);
        physicalEvent.setCapacity(capacity);
        physicalEvent.setTicketPrice(ticket_price);
        List<Hall> halls = new ArrayList<>();
        halls =  repository.findByCapacityAndStatus(capacity, hall_type, status);
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
}
