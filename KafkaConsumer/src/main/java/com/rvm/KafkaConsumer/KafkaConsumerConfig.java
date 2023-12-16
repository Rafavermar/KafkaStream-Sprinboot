package com.rvm.KafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka Consumer configuration for Spring Boot.
 * <p>
 * This class provides the necessary configurations to establish a connection with
 * a Kafka server and consume messages from specific topics. It defines the basic consumer
 * configurations and the factory for creating consumer listener instances.
 * </p>
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Address of the Kafka bootstrap servers, usually the address
     * of the Kafka broker.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Specific configurations for the Kafka consumer.
     * <p>
     * Defines properties such as the server address, group ID,
     * and deserializer classes for message keys and values.
     * </p>
     *
     * @return A map of consumer configurations.
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * Defines the factory for creating consumer listener instances.
     * <p>
     * This factory is responsible for creating the containers used
     * to consume messages from the Kafka server.
     * </p>
     *
     * @return A configured instance of {@link ConcurrentKafkaListenerContainerFactory}.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        return factory;
    }
}
