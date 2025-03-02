package com.example.meter2_service.services.kafka_producers;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.meter2_service.dtos.MeasurementCreatedEventDto;

import com.example2.library_kafka_dtos.MeasurementCreatedEventKafkaDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeasurementKafkaServiceImpl implements MeasurementKafkaService {

    private final KafkaTemplate<Long, MeasurementCreatedEventKafkaDto> kafkaTemplate;
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Long MeasurementSendToTopic(MeasurementCreatedEventDto measurementCreatedEventDto)
            throws InterruptedException, ExecutionException {

        MeasurementCreatedEventKafkaDto measurementCreatedEventKafkaDto = MeasurementCreatedEventKafkaDto
                .builder().id(measurementCreatedEventDto.getId())
                .meterId(measurementCreatedEventDto.getMeterId())
                .currentFlowRate(measurementCreatedEventDto.getCurrentFlowRate())
                .averageDailyConsumption(measurementCreatedEventDto.getAverageDailyConsumption())
                .gasPressure(measurementCreatedEventDto.getGasPressure())
                .gasTemperature(measurementCreatedEventDto.getGasTemperature())
                .errorCode(measurementCreatedEventDto.getErrorCode())
                .measurementDateTime(measurementCreatedEventDto.getMeasurementDateTime())
                // .valveStatus(measurementCreatedEventDto.getValveStatus())
                .recordingToDbDateTime(measurementCreatedEventDto.getRecordingToDbDateTime())
                .build();

        SendResult<Long, MeasurementCreatedEventKafkaDto> result = kafkaTemplate.send("topic-measurement",
                measurementCreatedEventDto.getMeterId(), measurementCreatedEventKafkaDto).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());

        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        return measurementCreatedEventDto.getMeterId();
    }

}