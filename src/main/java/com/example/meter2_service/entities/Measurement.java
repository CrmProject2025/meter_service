package com.example.meter2_service.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    // приходит с другого сервера
    @Column(nullable = false)
    private int value;

    // приходит с другого сервера
    @Column(nullable = false)
    private long meter_id;

    // приходит с другого сервера
    @Column(nullable = false)
    private LocalDateTime measurementDate;

    public Measurement(int value, long meter_id, LocalDateTime measurementDate) {
        this.value = value;
        this.measurementDate = measurementDate;
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
        return "Measurement [id=" + id + ", value=" + value + ", measurementDate=" + measurementDate + ", meter="
                + meter_id + "]";
    }

}