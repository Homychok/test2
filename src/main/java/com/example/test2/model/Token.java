package com.example.test2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;
@Entity
public class Token {
    @Id
    private String uuid;
    private Instant expiredDate;
    @ManyToOne
    private SerialSecret serialSecret;

    public Token() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }

    public SerialSecret getSerialSecret() {
        return serialSecret;
    }

    public void setSerialSecret(SerialSecret serialSecret) {
        this.serialSecret = serialSecret;
    }
}
