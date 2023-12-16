package com.rvm.KafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller that provides endpoints for interacting with a Kafka server.
 * <p>
 * This controller allows sending messages to the Kafka server on a specific topic via
 * an HTTP GET request.
 * </p>
 */
@RestController
public class KafkaProducerController {

    /**
     * Kafka Template that facilitates the production of messages to Kafka topics.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * The name of the topic to which the messages will be sent.
     */
    private static final String TOPIC = "input_topic";

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    /**
     * Endpoint to send a message to the Kafka topic.
     *
     * @param message The message to be sent to the Kafka topic.
     * @return A string indicating that the message has been sent.
     */
    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        logger.info("Sending message to topic {}: {}", TOPIC, message);
        kafkaTemplate.send(TOPIC, message);
        return "Message sent: " + message;
    }
}
