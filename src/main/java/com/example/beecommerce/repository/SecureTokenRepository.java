package com.example.beecommerce.repository;

import com.example.beecommerce.pojo.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {
    SecureToken findByToken(String token);

}