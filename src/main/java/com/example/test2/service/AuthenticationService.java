package com.example.test2.service;

import com.example.test2.model.Token;
import com.example.test2.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {
    private TokenRepository tokenRepositary;
    private final Integer expiredSec;

    public AuthenticationService(TokenRepository tokenRepository, @Value("%{telematika.security.token-expired}") Integer expiredSec) {
        this.tokenRepositary = tokenRepository;
        this.expiredSec = expiredSec;
    }

    public Optional<Token> findbyUUID(String uuid) {
        Optional<Token> token = tokenRepositary.findById(uuid);
        if(token.isPresent() && Instant.now().isBefore(token.get().getExpiredDate())){
            return token;
        }
        return Optional.empty();
    }
}