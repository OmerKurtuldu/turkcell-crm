package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.AddressService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;

import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;

import com.turkcell.CustomerService.core.utilities.mapping.ModelMapperService;
import com.turkcell.CustomerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Address;
import com.turkcell.CustomerService.entities.concretes.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressManager implements AddressService {

    private final ModelMapperService modelMapperService;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest) {
        Address address = this.modelMapperService.forRequest().map(createdAddressRequest, Address.class);
        Customer customer = customerRepository.findById(createdAddressRequest.getCustomerId()).orElseThrow();
        address.setCustomer(customer);
        addressRepository.save(address);
        CreatedAddressResponse addressResponse = this.modelMapperService.forResponse().map(address, CreatedAddressResponse.class);
        return addressResponse;
    }

}
