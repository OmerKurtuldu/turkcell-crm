package com.turkcell.CustomerService.business.abstracts;

import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedAddressResponse;
import com.turkcell.commonpackage.utils.dto.ClientResponse;

import java.util.List;


public interface AddressService {

    CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest);

    UpdatedAddressResponse update(UpdatedAddressRequest updatedAddressRequest);

    GetAddressResponse getById(int id);

    List<GetAllAddressResponse> getAll();

    void delete(int id);

    ClientResponse checkIfAddressAvailable(int id);

}
