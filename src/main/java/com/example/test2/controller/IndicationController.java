package com.example.test2.controller;

import com.example.test2.dto.LogIndication;
import com.example.test2.model.SerialSecret;
import com.example.test2.model.Token;
import com.example.test2.service.IndicationService;
import com.example.test2.service.SerialSecretService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indications")
public class IndicationController {

    private IndicationService indicationService;
    private SerialSecretService serialSecretService;

    public IndicationController(IndicationService indicationService, SerialSecretService serialSecretService) {
        this.indicationService = indicationService;
        this.serialSecretService = serialSecretService;
    }

    @PostMapping("/login")
    public Token logIn(@RequestBody SerialSecret serialSecret) {
        return serialSecretService.generateToken(serialSecret.getSerial(), serialSecret.getSecret());
    }

    @PostMapping
    public void getIndication(@RequestBody LogIndication logIndication, Authentication authentication) {
        Token principal = (Token) authentication.getPrincipal();
        indicationService.save(
                principal.getSerialSecret(),
                logIndication
        );
    }

    @GetMapping("/{serial}")
    public Double getAvg(@PathVariable String serial) {
        return indicationService.calculateAvgIndication(serial);
    }

}