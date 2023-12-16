package com.rvm.KafkaConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for consuming messages from a Kafka topic and responding to them.
 * <p>
 * This service listens for messages from a specific topic in Kafka.
 * Upon receiving a message, the service sends a response to the defined
 * topic {@code TOPIC}.
 * </p>
 */
@Service
public class KafkaConsumerService {

    /**
     * Kafka template for interacting with Kafka, enabling the sending and receiving of messages.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Topic to which responses will be sent after consuming a message.
     */
    private static final String TOPIC = "consumed_topic";

    /**
     * Method to consume messages from the "output_topic" topic.
     * <p>
     * Upon receiving a message from "output_topic", this method prints the
     * consumed message and sends a response to the topic {@code TOPIC}.
     * </p>
     *
     * @param message The message consumed from the "output_topic" topic.
     */
    @KafkaListener(topics = "output_topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        kafkaTemplate.send(TOPIC, "Response to: " + message);
    }
}
