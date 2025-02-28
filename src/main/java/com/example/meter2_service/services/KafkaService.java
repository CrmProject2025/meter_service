package com.example.meter2_service.services;

import java.util.concurrent.ExecutionException;

import com.example.meter2_service.dtos.KafkaDto;

public interface KafkaService {

    String createKafkaDto(KafkaDto kafkaDto) throws InterruptedException, ExecutionException;

}