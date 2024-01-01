package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.Transport;
import com.example.beecommerce.pojo.requests.TransportRequest;
import com.example.beecommerce.repository.TransportRepository;
import com.example.beecommerce.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportImplementService implements TransportService {
    @Autowired
    private TransportRepository transportRepository;

    @Override
    public Transport save(TransportRequest transportRequest) {
        Transport transport = new Transport();
        transport.setName(transportRequest.getName());
        transport.setPrice(transportRequest.getPrice());
        return transportRepository.save(transport);
    }

    @Override
    public List<Transport> getListTransport() {
        return transportRepository.findAll();
    }

    @Override
    public void deleteTransport(Long id) {
        Transport transport = transportRepository.findTransportById(id);
        if (transport == null) {
            throw new RuntimeException("Cannot find transport by id: " + id);
        }
        transportRepository.delete(transport);
    }
}
