package com.turkcell.customerService.kafka.producer;

import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);

    private final KafkaTemplate<String, CreatedCustomerEvent> kafkaTemplate;

    public void sendMessage(CreatedCustomerEvent event) {
        LOGGER.info(String.format("Customer added =>%s", event.toString()));

        Message<CreatedCustomerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "customer_topics")
                .build();

        kafkaTemplate.send(message);
    }
}