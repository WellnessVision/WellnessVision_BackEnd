package com.example.WellnessVision.service;

import com.example.WellnessVision.model.HealthProfessionalRegistrationRequest;
import com.example.WellnessVision.model.Volunteer;
import com.example.WellnessVision.model.VolunteerRegistrationRequest;
import com.example.WellnessVision.repository.VolunteerRegistrationRequestRepository;
import com.example.WellnessVision.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerRegistrationRequestService {

    @Autowired
    VolunteerRegistrationRequestRepository volunteerRegistrationRequestRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    public void volunteerRegistrationRequestSave(VolunteerRegistrationRequest newVolunteerRegistrationRequest){
         volunteerRegistrationRequestRepository.save(newVolunteerRegistrationRequest);
    }

    public List<VolunteerRegistrationRequest> viewVolunteerRegistrationRequestForAdmin(){
        return volunteerRegistrationRequestRepository.viewVolunteerRegistrationRequestForAdmin();
    }

    public VolunteerRegistrationRequest viewOneVolunteerRegistrationRequestForAdmin(int requestId){
        return volunteerRegistrationRequestRepository.viewOneVolunteerRegistrationRequestForAdmin(requestId);
    }

    public void volunteerSave(Volunteer newVolunteer){
        volunteerRepository.save(newVolunteer);
    }

    public void deleteVolunteerRegistrationRequest(int requestId){
        volunteerRegistrationRequestRepository.deleteById(requestId);
    }

}
