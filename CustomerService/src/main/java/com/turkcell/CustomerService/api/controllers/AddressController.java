package com.turkcell.CustomerService.api.controllers;

import com.turkcell.CustomerService.business.abstracts.AddressService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/individualcustomerservice/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAddressResponse add(@Valid @RequestBody CreatedAddressRequest createdAddressRequest) {
        return addressService.add(createdAddressRequest);
    }

}
