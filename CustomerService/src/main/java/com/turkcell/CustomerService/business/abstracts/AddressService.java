package com.turkcell.CustomerService.business.abstracts;

import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;


public interface AddressService {

    CreatedAddressResponse add(CreatedAddressRequest createdAddressRequest);

}
