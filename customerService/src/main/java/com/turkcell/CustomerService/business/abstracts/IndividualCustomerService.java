package com.turkcell.CustomerService.business.abstracts;

import com.turkcell.CustomerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.CustomerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreatedIndividualCustomerRequest createIndividualCustomerRequest);
    UpdatedIndividualCustomerResponse update(UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest);
    GetIndividualCustomerResponse getById(int id);
    List<GetAllIndividualCustomerResponse> getAll();
    void delete(int id);
}
