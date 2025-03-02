package com.example.meter2_service.services.kafka_producers;

import java.util.concurrent.ExecutionException;

import com.example.meter2_service.dtos.MeasurementCreatedEventDto;

public interface MeasurementKafkaService {

    Long MeasurementSendToTopic(MeasurementCreatedEventDto measurementCreatedEventDto) throws InterruptedException, ExecutionException;

}