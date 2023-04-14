package com.example.test2.sheduler;

import com.example.test2.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Component
public class ClearTokensJob {
    private TokenRepository tokenRepository;

    public ClearTokensJob(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void clearTokens(){
        log.info("Clearing tokens");
        tokenRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }
}
