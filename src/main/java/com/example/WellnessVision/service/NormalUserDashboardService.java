package com.example.WellnessVision.service;

import com.example.WellnessVision.model.PhysicalEvent;
import com.example.WellnessVision.repository.NormalUserGetPhysicalEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NormalUserDashboardService {

    @Autowired
    private NormalUserGetPhysicalEventsRepository normalUserGetPhysicalEventsRepository;

    public List<PhysicalEvent> getAllPhysicalEvents(){
        return normalUserGetPhysicalEventsRepository.getPhysicalEventsForUser();
    }

    public List<PhysicalEvent> getAllBookedPhysicalEventsByUserId(int user_id){
        return normalUserGetPhysicalEventsRepository.getBookedPhysicalEventsForUserByUserId(user_id);
    }



}
