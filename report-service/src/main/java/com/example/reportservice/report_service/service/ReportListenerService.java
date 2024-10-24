package com.example.reportservice.report_service.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ReportListenerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "user-preference-output", groupId = "report-service-group")
    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000))
    public void listen(ConsumerRecord<String, String> record) {
        log.info("User with userId: {} has preference: {}", record.key(), record.value());
    }
}