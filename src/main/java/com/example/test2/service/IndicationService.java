package com.example.test2.service;

import com.example.test2.dto.LogIndication;
import com.example.test2.model.Indication;
import com.example.test2.model.SerialSecret;
import com.example.test2.repository.IndicationRepository;
import org.springframework.stereotype.Service;

@Service
public class IndicationService {

    private final IndicationRepository indicationRepository;

    public IndicationService(IndicationRepository indicationRepository) {
        this.indicationRepository = indicationRepository;
    }

    public Double calculateAvgIndication(String serial) {
        return indicationRepository.getAvgIndication(serial);
    }

    public void save(SerialSecret serialSecret, LogIndication logIndication) {
        Indication indication = new Indication();
        indication.setValue(logIndication.getValue());
        indication.setSerialSecret(serialSecret);
        indicationRepository.save(indication);
    }
}