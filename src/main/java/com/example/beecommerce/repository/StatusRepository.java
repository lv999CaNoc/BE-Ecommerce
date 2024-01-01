package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
    Status getStatusById(Long id);
}
