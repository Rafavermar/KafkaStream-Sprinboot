package com.rvm.KafkaStreamer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka Streams in a Spring application.
 * <p>
 * This class sets up the necessary configuration for Kafka Streams processing,
 * including server details and default SerDes for keys and values.
 * </p>
 */
@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    /**
     * The bootstrap servers for the Kafka cluster.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsConfig.class);

    /**
     * Configures and provides the StreamsConfig bean.
     *
     * @return The configuration for the Kafka Streams application.
     */
    @Bean
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return new StreamsConfig(props);
    }

    /**
     * Defines and provides the Kafka Stream for processing.
     *
     * @return The Kafka Stream for processing messages.
     */
    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {
        Serde<String> stringSerde = Serdes.String();
        KStream<String, String> stream = builder.stream("input_topic", Consumed.with(stringSerde, stringSerde));

        stream.peek((key, value) -> logger.info("Received message - key: {}, value: {}", key, value))
                .mapValues(value -> {
                    String processedValue = "Processed: " + value;
                    logger.info("Processing message: {}", processedValue);
                    return processedValue;
                })
                .peek((key, value) -> logger.info("Sending processed message - key: {}, value: {}", key, value))
                .to("output_topic", Produced.with(stringSerde, stringSerde));

        return stream;
    }
}
