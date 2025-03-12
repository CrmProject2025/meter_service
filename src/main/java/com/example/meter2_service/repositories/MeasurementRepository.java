package com.example.meter2_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meter2_service.entities.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}
