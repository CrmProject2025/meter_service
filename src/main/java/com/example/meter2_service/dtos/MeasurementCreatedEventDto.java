package com.example.meter2_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MeasurementCreatedEventDto {

    @NotBlank
    private Long id;

    @NotBlank
    private Long meterId;

    @NotBlank
    private BigDecimal currentFlowRate;

    @NotBlank
    private BigDecimal averageDailyConsumption;

    @NotBlank
    private BigDecimal gasPressure;

    @NotBlank
    private BigDecimal gasTemperature;

    @NotBlank
    private Boolean valveStatus;

    @NotBlank
    @Size(min = 0, max = 255)
    private String errorCode;

    @NotBlank
    private LocalDateTime measurementDateTime;

    @NotBlank
    private LocalDateTime recordingToDbDateTime;

}
