package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.Status;
import com.example.beecommerce.pojo.requests.StatusRequest;
import com.example.beecommerce.repository.StatusRepository;
import com.example.beecommerce.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusImplementService implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status createStatus(StatusRequest statusRequest) {
        Status status = new Status();
        status.setName_status(statusRequest.getName_status());
        return statusRepository.save(status);
    }
}
