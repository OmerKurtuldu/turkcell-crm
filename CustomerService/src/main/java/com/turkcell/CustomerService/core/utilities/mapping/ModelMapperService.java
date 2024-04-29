package com.turkcell.CustomerService.core.utilities.mapping;

import com.turkcell.CustomerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.CustomerService.entities.concretes.IndividualCustomer;
import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();

}
