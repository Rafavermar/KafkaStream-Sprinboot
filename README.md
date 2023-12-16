# Kafka Microservices Project

This repository contains a simple Kafka-based microservices project using Spring Boot. It demonstrates a basic setup with a Kafka Producer, Kafka Streams for processing, and a Kafka Consumer.

## Components

- **Kafka Producer**: A Spring Boot application that sends messages to a Kafka topic.
- **Kafka Streams**: Processes the messages received from the Producer, performing transformations before forwarding them to another topic.
- **Kafka Consumer**: Consumes and responds to messages processed by Kafka Streams.

## Setup

1. **Kafka Environment**: Ensure Zookeeper and Kafka Server are up and running.
2. **Run Applications**: Start the Producer, Kafka Streams, and Consumer applications in the given order.

## Usage

Send messages via the Producer's REST endpoint and observe the processed messages being consumed by the Consumer. Logs in each service provide insights into the message flow through the system.

## Configuration

Refer to the respective `application.properties` files in each module for Kafka and application-specific configurations.

## Dependencies

- Spring Boot
- Apache Kafka
- Kafka Streams

## Contributing

Contributions to enhance this project are welcome. Please adhere to the contribution guidelines outlined in this repository.
