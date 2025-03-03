package com.example.meter2_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MeasurementCreatedEventDto {

    @NotNull
    private Long meterId;

    @NotNull
    private BigDecimal currentFlowRate;

    @NotNull
    private BigDecimal averageDailyConsumption;

    @NotNull
    private BigDecimal gasPressure;

    @NotNull
    private BigDecimal gasTemperature;

    @NotNull
    private Boolean valveStatus;

    @NotBlank
    @Size(min = 0, max = 255)
    private String errorCode;

    @NotNull
    private LocalDateTime measurementDateTime;

}
