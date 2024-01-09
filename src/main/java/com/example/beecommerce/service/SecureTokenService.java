package com.example.beecommerce.service;

import com.example.beecommerce.pojo.entity.SecureToken;
import com.example.beecommerce.pojo.entity.User;

public interface SecureTokenService {
    SecureToken createSecureToken(User user);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);
}
