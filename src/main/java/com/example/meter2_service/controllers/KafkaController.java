package com.example.meter2_service.controllers;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example2.library_kafka_dtos.KafkaEventMy;
import com.example.meter2_service.dtos.KafkaDto;
import com.example.meter2_service.services.KafkaServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class KafkaController {

    private KafkaServiceImpl kafkaServiceImpl;
    private KafkaTemplate<String, KafkaEventMy> kafkaTemplate;

    @PostMapping("/kafka_a")
    public ResponseEntity<String> postMethodName(@RequestBody KafkaDto kafkaDto)
            throws InterruptedException, ExecutionException {
        // String kafkaId = kafkaServiceImpl.createKafkaDto(kafkaDto);
        String kafkaId = UUID.randomUUID().toString();
        KafkaEventMy kafkaEvent = new KafkaEventMy(kafkaId, kafkaDto.getTitle(),
                kafkaDto.getQuantity());

        kafkaTemplate.send("topic_a", kafkaEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sent to topic-a: " + kafkaId);
    }
}