package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.AddressService;
import com.turkcell.CustomerService.business.abstracts.CityService;
import com.turkcell.CustomerService.business.abstracts.CustomerService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedAddressResponse;
import com.turkcell.CustomerService.business.rules.AddressBusinessRules;
import com.turkcell.CustomerService.business.rules.CityBusinessRules;
import com.turkcell.CustomerService.business.rules.CustomerBusinessRules;
import com.turkcell.CustomerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.CityRepository;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Address;
import com.turkcell.CustomerService.entities.concretes.City;
import com.turkcell.CustomerService.entities.concretes.Customer;
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
    private final CityBusinessRules cityBusinessRules;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest) {
        customerBusinessRules.customerShouldBeExist(createdAddressRequest.getCustomerId());
        cityBusinessRules.cityShouldBeExist(createdAddressRequest.getCityId());

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
        customerBusinessRules.customerShouldBeExist(updatedAddressRequest.getCustomerId());
        cityBusinessRules.cityShouldBeExist(updatedAddressRequest.getCityId());

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
