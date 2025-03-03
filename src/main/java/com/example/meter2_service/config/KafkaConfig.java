package com.example.meter2_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
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
    public NewTopic createTopic() {
        return TopicBuilder.name("topic-measurement")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

    @Bean
    public ProducerFactory<Long, MeasurementCreatedEventKafkaDto> producerFactory() {
        // Настройка свойств для подключения к Kafka
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:10092,localhost:11092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Настройки подтверждений и ретраев
        configProps.put(ProducerConfig.ACKS_CONFIG, "all"); // подтверждение от всех реплик
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3); // Количество ретраев при ошибке отправки

        // Включаем идемпотентность
        configProps.put("enable.idempotence", true);
        // сообщений в формате JSON
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Long, MeasurementCreatedEventKafkaDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}