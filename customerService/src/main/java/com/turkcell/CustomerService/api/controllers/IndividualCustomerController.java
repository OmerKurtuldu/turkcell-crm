package com.turkcell.CustomerService.api.controllers;

import com.turkcell.CustomerService.business.abstracts.IndividualCustomerService;
import com.turkcell.CustomerService.business.dtos.request.create.CreatedIndividualCustomerRequest;
import com.turkcell.CustomerService.business.dtos.request.update.UpdatedIndividualCustomerRequest;
import com.turkcell.CustomerService.business.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.getAll.GetAllIndividualCustomerResponse;
import com.turkcell.CustomerService.business.dtos.response.updated.UpdatedIndividualCustomerResponse;
import com.turkcell.CustomerService.kafka.producer.CustomerProducer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/individualcustomerservice/api/v1/customers")
public class IndividualCustomerController {

    private IndividualCustomerService individualCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreatedIndividualCustomerRequest createdCustomerRequest) {
        return individualCustomerService.add(createdCustomerRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse add(@Valid @RequestBody UpdatedIndividualCustomerRequest updatedIndividualCustomerRequest) {
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
    public void delete(@PathVariable int id) {
        individualCustomerService.delete(id);
    }
}
