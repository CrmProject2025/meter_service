package com.example.meter2_service.services.MeasurementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.meter2_service.dtos.MeasurementCreatedEventDto;
import com.example.meter2_service.entities.Measurement;
import com.example.meter2_service.repositories.MeasurementRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private MeasurementRepository measurementRepository;
    private static final Logger logger = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    @Override
    public Long createMeasurement(MeasurementCreatedEventDto measurementCreatedEventDto) {
        Measurement measurement = new Measurement(measurementCreatedEventDto.getMeterId(),
                measurementCreatedEventDto.getCurrentFlowRate(),
                measurementCreatedEventDto.getAverageDailyConsumption(),
                measurementCreatedEventDto.getGasPressure(),
                measurementCreatedEventDto.getGasTemperature(),
                measurementCreatedEventDto.getValveStatus(),
                measurementCreatedEventDto.getErrorCode(),
                measurementCreatedEventDto.getMeasurementDateTime());
        Measurement savedMeasurement = measurementRepository.save(measurement);
        logger.info("Creating measurement for meter(id): {}", measurementCreatedEventDto.getMeterId());
        return savedMeasurement.getId();
    }
}
