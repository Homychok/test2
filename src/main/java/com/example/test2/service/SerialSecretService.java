package com.example.test2.service;


import com.example.test2.model.SerialSecret;
import com.example.test2.model.Token;
import com.example.test2.repository.SerialSecretRepository;
import com.example.test2.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class SerialSecretService {

    private final SerialSecretRepository serialSecretRepository;
    private final TokenRepository tokenRepository;
    @Value("${telematika.security.token-expired}")
    private Integer expiredTime;

    public SerialSecretService(SerialSecretRepository serialSecretRepository, TokenRepository tokenRepository) {
        this.serialSecretRepository = serialSecretRepository;
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(String serial, String secret) {
        SerialSecret serialSecret = serialSecretRepository.findBySerial(serial);
        if(serialSecret != null && serialSecret.getSecret().equals(secret)) {
            Token token = new Token();
            token.setUuid(UUID.randomUUID().toString());
            token.setExpiredDate(Instant.now().plus(expiredTime, ChronoUnit.SECONDS));
            token.setSerialSecret(serialSecret);
            tokenRepository.save(token);
            return token;
        }
        return null;
    }

}
