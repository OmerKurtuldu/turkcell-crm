package com.turkcell.accountService.consume;

import com.turkcell.accountService.dataAccess.abstracts.AddressRepository;
import com.turkcell.accountService.dataAccess.abstracts.CityRepository;
import com.turkcell.accountService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.accountService.entities.concretes.Address;
import com.turkcell.accountService.entities.concretes.City;
import com.turkcell.accountService.entities.concretes.Customer;
import com.turkcell.commonpackage.events.address.CreatedAddressEvent;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressConsumer {
    private AddressRepository addressRepository;
    private CityRepository cityRepository;
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressConsumer.class);

    @KafkaListener(
            topics = "address_topics", groupId = "account"
    )

    public void consume(CreatedAddressEvent event) {
        Address address = this.modelMapperService.forRequest().map(event, Address.class);
        address.setId(event.getId());
        City city = cityRepository.findById(event.getCityId()).orElseThrow();
        address.setCity(city);
        Customer customer = customerRepository.findById(event.getCustomerId()).orElseThrow();
        address.setCustomer(customer);
        addressRepository.save(address);

        System.out.println(customer.toString());
        LOGGER.info(String.format("Address event recieved in account service => %s", event.toString()));


    }

}
