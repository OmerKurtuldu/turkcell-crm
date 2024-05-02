package com.turkcell.searchService.kafka.consume;

import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import com.turkcell.searchService.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume(CreatedCustomerEvent event){
        Customer customer = new Customer();
        customer.setBirthDate(event.getBirthDate());
        customer.setGender(event.getGenderType().name().toString());
        customer.setFatherName(event.getFatherName());
        customer.setLastName(event.getLastName());
        customer.setFirstName(event.getFirstName());
        customer.setMotherName(event.getMotherName());
        customer.setNationalityNumber(event.getNationalityNumber());
        customer.setSecondName(event.getSecondName());
        System.out.println(customer.toString());
        LOGGER.info(String.format("Customer event recieved in stock service => %s", event.toString()));


    }

}