package com.turkcell.CustomerService.kafka.producer;

import com.turkcell.commonpackage.events.address.CreatedAddressEvent;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressProducer.class);

    private final KafkaTemplate<String, CreatedAddressEvent> kafkaTemplate;

    public void sendMessage(CreatedAddressEvent event) {
        LOGGER.info(String.format("Address added =>%s", event.toString()));

        Message<CreatedAddressEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"address_topics")
                .build();

        kafkaTemplate.send(message);
    }
}
