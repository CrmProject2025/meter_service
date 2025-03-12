package com.example.meter2_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example2.library_kafka_dtos.MeasurementCreatedEventKafkaDto;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public NewTopic createTopicMeasurement() {
        return TopicBuilder.name("topic-measurement")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2",
                        "retention.ms", "604800000", // 7 дней хранения
                        "cleanup.policy", "delete", // Удаление старых сообщений
                        "compression.type", "snappy" // Сжатие для экономии места
                )).build();
    }

    @Bean
    public NewTopic createTopicLogs() {
        return TopicBuilder.name("topic-logs")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2",
                        "retention.ms", "604800000",
                        "cleanup.policy", "delete",
                        "compression.type", "snappy"))
                .build();
    }

    @Bean
    public ProducerFactory<Long, MeasurementCreatedEventKafkaDto> producerFactoryMeasurement() {
        // Настройка свойств для подключения к Kafka
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:10092,localhost:11092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Настройки подтверждений и ретраев
        configProps.put(ProducerConfig.ACKS_CONFIG, "1"); 
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3); // Количество ретраев при ошибке отправки

        // Включаем идемпотентность
        // configProps.put("enable.idempotence", true);
        // сообщений в формате JSON
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @Qualifier("kafkaTemplateMeasurement")
    public KafkaTemplate<Long, MeasurementCreatedEventKafkaDto> kafkaTemplateMeasurement() {
        return new KafkaTemplate<>(producerFactoryMeasurement());
    }

    @Bean
    public ProducerFactory<Long, MeasurementCreatedEventKafkaDto> producerFactoryLogs() {
        // Настройка свойств для подключения к Kafka
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:10092,localhost:11092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Настройки подтверждений и ретраев
        configProps.put(ProducerConfig.ACKS_CONFIG, "1"); // подтверждение от всех реплик
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3); // Количество ретраев при ошибке отправки

        // Включаем идемпотентность
        // configProps.put("enable.idempotence", true);
        // сообщений в формате JSON
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @Qualifier("kafkaTemplateLogs")
    public KafkaTemplate<Long, MeasurementCreatedEventKafkaDto> kafkaTemplateLogs() {
        return new KafkaTemplate<>(producerFactoryLogs());
    }
}