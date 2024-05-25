package com.turkcell.customerService.business.concretes;

import com.turkcell.customerService.business.abstracts.AddressService;
import com.turkcell.customerService.business.abstracts.CityService;
import com.turkcell.customerService.business.abstracts.CustomerService;
import com.turkcell.customerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.customerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedAddressResponse;
import com.turkcell.customerService.business.rules.AddressBusinessRules;
import com.turkcell.customerService.business.rules.CityBusinessRules;
import com.turkcell.customerService.business.rules.CustomerBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.customerService.entities.concretes.Address;
import com.turkcell.customerService.entities.concretes.City;
import com.turkcell.customerService.entities.concretes.Customer;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AddressManager implements AddressService {

    private final ModelMapperService modelMapperService;
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    private final CityService cityService;
    private final AddressBusinessRules addressBusinessRules;

    @Override
    public CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest) {
        customerService.getById(createdAddressRequest.getCustomerId());
        cityService.getById(createdAddressRequest.getCityId());

        Address address = this.modelMapperService.forRequest().map(createdAddressRequest, Address.class);

        Customer customer = customerService.getById(createdAddressRequest.getCustomerId());
        address.setCustomer(customer);

        City city = cityService.getById(createdAddressRequest.getCityId());
        address.setCity(city);

        addressRepository.save(address);

        CreatedAddressResponse addressResponse = this.modelMapperService.forResponse().map(address, CreatedAddressResponse.class);
        return addressResponse;
    }

    @Override
    public UpdatedAddressResponse update(UpdatedAddressRequest updatedAddressRequest) {
        addressBusinessRules.addressShouldBeExist(updatedAddressRequest.getId());
        customerService.getById(updatedAddressRequest.getCustomerId());
        cityService.getById(updatedAddressRequest.getCityId());

        Address address = this.modelMapperService.forRequest().map(updatedAddressRequest, Address.class);

        Customer customer = customerService.getById(updatedAddressRequest.getCustomerId());
        address.setCustomer(customer);

        City city = cityService.getById(updatedAddressRequest.getCustomerId());
        address.setCity(city);

        addressRepository.save(address);

        UpdatedAddressResponse updatedAddressResponse = this.modelMapperService.forResponse().map(address, UpdatedAddressResponse.class);
        return updatedAddressResponse;
    }

    @Override
    public GetAddressResponse getById(int id) {
        addressBusinessRules.addressShouldBeExist(id);

        Optional<Address> address = addressRepository.findById(id);

        GetAddressResponse getAddressResponse = this.modelMapperService.forResponse().map(address.get(), GetAddressResponse.class);
        return getAddressResponse;
    }

    @Override
    public List<GetAllAddressResponse> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(address -> this.modelMapperService.forResponse().map(address, GetAllAddressResponse.class)).toList();
    }

    @Override
    public void delete(int id) {
        addressBusinessRules.addressShouldBeExist(id);

        addressRepository.deleteById(id);
    }

    @Override
    public ClientResponse checkIfAddressAvailable(int id) {
        var response = new ClientResponse();
        validateAddressAvailability(id, response);
        return response;
    }

    private void validateAddressAvailability(int id, ClientResponse response) {
        try {
            addressBusinessRules.addressShouldBeExist(id);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
        }
    }

}
