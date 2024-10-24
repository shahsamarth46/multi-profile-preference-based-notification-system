package com.example.carsystem.car_system_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class CarSystemListenerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "user-preference-output", groupId = "car-system-group")
    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000))
    public void listen(String message) {
        log.info("Car System service received message: {}", message);
    }

}