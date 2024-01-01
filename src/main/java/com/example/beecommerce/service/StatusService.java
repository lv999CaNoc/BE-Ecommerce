package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Status;
import com.example.beecommerce.pojo.requests.StatusRequest;

public interface StatusService{
    Status createStatus(StatusRequest statusRequest);
}
