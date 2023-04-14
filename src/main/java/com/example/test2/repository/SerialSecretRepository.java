package com.example.test2.repository;

import com.example.test2.model.SerialSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialSecretRepository extends JpaRepository<SerialSecret, String> {
    SerialSecret findBySerial(String serial);
}
