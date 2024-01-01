package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment getPaymentById(Long id);
}
