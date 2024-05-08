package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.AddressService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;

import com.turkcell.CustomerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;

import com.turkcell.CustomerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedAddressResponse;
import com.turkcell.CustomerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.CustomerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.CityRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Address;
import com.turkcell.CustomerService.entities.concretes.City;
import com.turkcell.CustomerService.entities.concretes.Customer;

import com.turkcell.CustomerService.kafka.producer.AddressProducer;
import com.turkcell.commonpackage.events.address.CreatedAddressEvent;
import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@RequiredArgsConstructor
@Service
public class AddressManager implements AddressService {

    private final ModelMapperService modelMapperService;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;
    private final AddressProducer addressProducer;

    @Override
    public CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest) {
        Address address = this.modelMapperService.forRequest().map(createdAddressRequest, Address.class);
        Customer customer = customerRepository.findById(createdAddressRequest.getCustomerId()).orElseThrow();
        address.setCustomer(customer);
        City city = cityRepository.findById(createdAddressRequest.getCityId()).orElseThrow();
        address.setCity(city);
        addressRepository.save(address);
        CreatedAddressEvent createdAddressEvent = this.modelMapperService.forResponse().map(address,CreatedAddressEvent.class);
        createdAddressEvent.setMessages("address status is in pending state");
        createdAddressEvent.setStatus("PENDING");
        addressProducer.sendMessage(createdAddressEvent);
        CreatedAddressResponse addressResponse = this.modelMapperService.forResponse().map(address, CreatedAddressResponse.class);
        return addressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdatedAddressRequest updatedAddressRequest) {
        //todo: iş kuralı gelecek
        addressRepository.findById(updatedAddressRequest.getId()).orElseThrow();
        Address address = this.modelMapperService.forRequest().map(updatedAddressRequest,Address.class);
        Customer customer = customerRepository.findById(address.getCustomer().getId()).orElseThrow();
        address.setCustomer(customer);
        City city = cityRepository.findById(updatedAddressRequest.getCityId()).orElseThrow();
        address.setCity(city);
        addressRepository.save(address);
        UpdatedAddressResponse updatedAddressResponse = this.modelMapperService.forResponse().map(address, UpdatedAddressResponse.class);
        return updatedAddressResponse;
    }

    @Override
    public GetAddressResponse getById(int id) {

        Address address = addressRepository.findById(id).orElseThrow(null);

        GetAddressResponse getAddressResponse = this.modelMapperService.forResponse().map(address,GetAddressResponse.class);

        return getAddressResponse;
    }

    @Override
    public List<GetAllAddressResponse> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(address -> this.modelMapperService.forResponse().map(address,GetAllAddressResponse.class)).toList();
    }

    @Override
    public void delete(int id) {
        //todo iş kuralı
        addressRepository.deleteById(id);
    }

}
