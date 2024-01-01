package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.Transport;
import com.example.beecommerce.pojo.requests.TransportRequest;

import java.util.List;

public interface TransportService {
    Transport save(TransportRequest transportRequest);

    List<Transport> getListTransport();

    void deleteTransport(Long id);
}
