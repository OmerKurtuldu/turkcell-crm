package com.turkcell.accountService.consume;


import com.turkcell.accountService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.accountService.entities.concretes.Customer;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerConsumer {
    private CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.name}", groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume(CreatedCustomerEvent event) {
        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setBirthDate(event.getBirthDate());
        customer.setGender(event.getGenderType().name().toString());
        customer.setFatherName(event.getFatherName());
        customer.setLastName(event.getLastName());
        customer.setFirstName(event.getFirstName());
        customer.setMotherName(event.getMotherName());
        customer.setNationalityNumber(event.getNationalityNumber());
        customer.setSecondName(event.getSecondName());

        customerRepository.save(customer);

        System.out.println(customer.toString());
        LOGGER.info(String.format("Customer event recieved in stock service => %s", event.toString()));


    }

}
