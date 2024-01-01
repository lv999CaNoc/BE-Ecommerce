package com.example.beecommerce.controller;

import com.example.beecommerce.pojo.entity.Status;
import com.example.beecommerce.pojo.requests.StatusRequest;
import com.example.beecommerce.pojo.responses.ObjectResponse;
import com.example.beecommerce.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@CrossOrigin(maxAge = 3600)
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping("/create")
    ResponseEntity<ObjectResponse> create(@RequestBody StatusRequest statusRequest) {
        Status status = statusService.createStatus(statusRequest);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK, "Create status successfully", status)
        );
    }
}
