package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface TransportRepository extends JpaRepository<Transport,Long> {
    Transport findTransportById(Long id);

    Transport findTransportByName(String name);
}
