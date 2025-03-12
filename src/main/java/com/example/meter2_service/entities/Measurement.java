package com.example.meter2_service.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long meterId;

    @Column(precision = 10, scale = 3, nullable = false)
    private BigDecimal currentFlowRate;

    @Column(precision = 10, scale = 3, nullable = false)
    private BigDecimal averageDailyConsumption;

    @Column(precision = 6, scale = 3, nullable = false)
    private BigDecimal gasPressure;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal gasTemperature;

    @Column(nullable = false)
    private boolean valveStatus;

    @Column(length = 255, nullable = false)
    private String errorCode;

    @Column(nullable = false)
    private LocalDateTime measurementDateTime;

    private LocalDateTime recordingToDbDateTime;

    @PrePersist
    private void init() {
        recordingToDbDateTime = LocalDateTime.now();
    }

    public Measurement(long meterId, BigDecimal currentFlowRate, BigDecimal averageDailyConsumption,
            BigDecimal gasPressure, BigDecimal gasTemperature, boolean valveStatus, String errorCode,
            LocalDateTime measurementDateTime) {
        this.meterId = meterId;
        this.currentFlowRate = currentFlowRate;
        this.averageDailyConsumption = averageDailyConsumption;
        this.gasPressure = gasPressure;
        this.gasTemperature = gasTemperature;
        this.valveStatus = valveStatus;
        this.errorCode = errorCode;
        this.measurementDateTime = measurementDateTime;
    }

    public Measurement() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Measurement other = (Measurement) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Measurement [id=" + id + ", meter_id=" + meterId + ", current_flow_rate=" + currentFlowRate
                + ", error_code=" + errorCode + "]";
    }

}