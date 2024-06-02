package com.turkcell.customerService.business.abstracts;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.customerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.customerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedAddressResponse;

import java.util.List;

public interface AddressService {

    CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest);

    UpdatedAddressResponse update(UpdatedAddressRequest updatedAddressRequest);

    GetAddressResponse getById(int id);

    List<GetAllAddressResponse> getAll();

    void delete(int id);

    ClientResponse checkIfAddressAvailable(int id);

}
