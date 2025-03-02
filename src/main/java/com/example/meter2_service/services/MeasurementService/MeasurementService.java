package com.example.meter2_service.services.MeasurementService;

import com.example.meter2_service.dtos.MeasurementCreatedEventDto;

public interface MeasurementService {
    void createMeasurement(MeasurementCreatedEventDto measurementCreatedEventDto);
}
