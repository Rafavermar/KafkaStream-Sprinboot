package com.rvm.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka producer configuration that provides the necessary settings and
 * Kafka template for sending messages to a Kafka server.
 * <p>
 * This class uses Spring configuration values (e.g., from the application.properties file)
 * to set specific Kafka properties.
 * </p>
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * The address of the Kafka server to which the producer should connect.
     * This address is usually in the format: {@code host1:port1,host2:port2,...}.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates and returns a configuration for the Kafka producer.
     *
     * @return A map of properties with configurations for the Kafka producer.
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * Creates and returns a Kafka template that uses the producer's configuration.
     * The Kafka template is used to send messages to Kafka topics.
     *
     * @return A configured instance of {@link KafkaTemplate}.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }
}
