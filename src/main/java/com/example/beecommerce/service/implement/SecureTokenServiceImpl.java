package com.example.beecommerce.service.implement;

import com.example.beecommerce.pojo.entity.SecureToken;
import com.example.beecommerce.pojo.entity.User;
import com.example.beecommerce.repository.SecureTokenRepository;
import com.example.beecommerce.service.SecureTokenService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class SecureTokenServiceImpl implements SecureTokenService {
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;
    private int tokenValidityInSeconds = 28800;

    @Autowired
    private SecureTokenRepository secureTokenRepository;

    @Override
    public SecureToken createSecureToken(User user) {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        SecureToken secureToken = SecureToken.builder()
                .user(user)
                .token(tokenValue)
                .expireAt(LocalDateTime.now().plusSeconds(tokenValidityInSeconds))
                .build();
        secureTokenRepository.save(secureToken);
        return secureToken;
    }

    @Override
    public SecureToken findByToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    @Override
    public void removeToken(SecureToken token) {
        secureTokenRepository.delete(token);
    }

}
