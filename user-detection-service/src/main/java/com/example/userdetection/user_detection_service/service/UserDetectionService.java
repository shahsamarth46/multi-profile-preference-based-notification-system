package com.example.userdetection.user_detection_service.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserDetectionService {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, String> kafkaTemplate;

    AtomicInteger counter = new AtomicInteger();

    public String sendUserDetectionEvent(String userId) {


        var message = MessageBuilder.withPayload("User : " + userId)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, String.valueOf(counter.incrementAndGet()))
                .setHeader("system", "user_detection_service")
                .build();

        var response = kafkaTemplate.send(message);

        try {
            var result = response.get();
            log.info("Message sent successfully: {}", result.getProducerRecord().value());
            log.info("Partition: {}", result.getRecordMetadata().partition());
            return result.getProducerRecord().value();
        } catch (ExecutionException | InterruptedException e) {
            log.error("Failed to send message:{}", e.getCause().getMessage());
            return e.getCause().getMessage();
        }

    }

}
