package com.turkcell.CustomerService.api.controllers;

import com.turkcell.CustomerService.business.abstracts.AddressService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.request.update.UpdatedAddressRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllAddressResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedAddressResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customerservice/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAddressResponse add(@Valid @RequestBody CreatedAddressRequest createdAddressRequest) {
        return addressService.add(createdAddressRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAddressResponse update(@Valid @RequestBody UpdatedAddressRequest updatedAddressRequest){
        return addressService.update(updatedAddressRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetAddressResponse getById(@PathVariable int id){
        return addressService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllAddressResponse> getAllAddressResponses(){
        return addressService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        addressService.delete(id);
    }
}