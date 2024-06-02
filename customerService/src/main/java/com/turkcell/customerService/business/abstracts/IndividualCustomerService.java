package com.turkcell.customerService.business.abstracts;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.customerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreatedIndividualCustomerRequest createIndividualCustomerRequest);

    UpdatedIndividualCustomerResponse update(UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest);

    GetIndividualCustomerResponse getById(int id);

    List<GetAllIndividualCustomerResponse> getAll();

    void delete(int id);

    ClientResponse checkIfCustomerAvailable(int id);

    void setStatus(int id);
}
