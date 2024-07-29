package com.example.WellnessVision.service;

import com.example.WellnessVision.model.DeletedPhysicalEvent;
import com.example.WellnessVision.repository.DeletedPhysicalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.io.IOException;

@Service
public class DeletePhysicalEventService {

    @Autowired
    private final DeletedPhysicalEventRepository deletedPhysicalEventRepository;

    public DeletePhysicalEventService(DeletedPhysicalEventRepository deletedPhysicalEventRepository) {
        this.deletedPhysicalEventRepository = deletedPhysicalEventRepository;
    }
    public List<DeletedPhysicalEvent> viewAllDeleteHealthProfessionalPhysicalEventForHP(int hp_id, String searchCode) throws IOException {
        String searchCodeModify = searchCode + "%";
        return deletedPhysicalEventRepository.viewAllDeleteHealthProfessionalPhysicalEventForHP(hp_id, searchCodeModify);
    }

    public DeletedPhysicalEvent viewOneDeleteHealthProfessionalPhysicalEventForHP(int event_id) throws IOException {
        return deletedPhysicalEventRepository.viewOneDeleteHealthProfessionalPhysicalEventForHP(event_id);
    }


}
