package com.turkcell.customerService.api.controllers;

import com.turkcell.customerService.business.abstracts.IndividualCustomerService;
import com.turkcell.customerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.customerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.customerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customerservice/api/v1/individualcustomers")
public class IndividualCustomerController {

    private IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreatedIndividualCustomerRequest createdCustomerRequest) {
        return individualCustomerService.add(createdCustomerRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@Valid @RequestBody UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest) {
        return individualCustomerService.update(updatedIndividualCustomerRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable int id) {
        return individualCustomerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllIndividualCustomerResponse> getAll() {
        return individualCustomerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDelete(@PathVariable int id) {
        individualCustomerService.delete(id);
    }

    @PutMapping("/setActive/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void setStatus(@PathVariable int id) {
        individualCustomerService.setStatus(id);
    }

    @GetMapping("/customerClient/{id}")
    public ClientResponse checkIfCustomerAvailable(@PathVariable int id){
        return individualCustomerService.checkIfCustomerAvailable(id);
    }
}
