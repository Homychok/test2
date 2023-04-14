package com.example.test2.repository;

import com.example.test2.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteAllByExpiredDateIsBefore(Instant now);

    Optional<Token> findById(String uuid);
}