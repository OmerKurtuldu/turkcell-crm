package com.turkcell.accountService.consume;


import com.turkcell.accountService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.accountService.entities.concretes.Customer;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;

import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerConsumer {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(
            topics = "customer_topics", groupId = "account"
    )

    public void consume(CreatedCustomerEvent event) {
        Customer customer = this.modelMapperService.forRequest().map(event, Customer.class);

        customerRepository.save(customer);

        System.out.println(customer.toString());
        LOGGER.info(String.format("Customer event recieved in stock service => %s", event.toString()));

    }

}
