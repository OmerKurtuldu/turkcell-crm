package com.turkcell.searchService.kafka.consume;


import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.searchService.entities.concretes.Customer;
import com.turkcell.searchService.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerConsumer.class);
    private final SearchRepository searchRepository;
    private final ModelMapperService modelMapperService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(CreatedCustomerEvent event){
        Customer customer = this.modelMapperService.forRequest().map(event, Customer.class);
        searchRepository.save(customer);
        System.out.println(customer.toString());
        LOGGER.info(String.format("Customer event recieved in stock service => %s", event.toString()));
    }

}
