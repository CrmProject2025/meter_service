package com.example.meter2_service.controllers;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.meter2_service.dtos.MeasurementCreatedEventDto;
import com.example.meter2_service.repositories.MeasurementRepository;
import com.example.meter2_service.services.MeasurementService.MeasurementService;
import com.example.meter2_service.services.kafka_producers.MeasurementKafkaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MeasurementController {
    private MeasurementKafkaService measurementKafkaService;
    private MeasurementService measurementService;

    @PostMapping("/measurement")
    public ResponseEntity<String> postMethodName(
            @Valid @RequestBody MeasurementCreatedEventDto measurementCreatedEventDto)
            throws InterruptedException, ExecutionException {

        // обработка исключений здесь или в сервисах, логирование, документация в коде над методами
        // логично перенести логику кафка в сервис по работе с сущность, сделать доп метод
        measurementService.createMeasurement(measurementCreatedEventDto);
        measurementKafkaService.MeasurementSendToTopic(measurementCreatedEventDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Measurement recording");
    }
}
