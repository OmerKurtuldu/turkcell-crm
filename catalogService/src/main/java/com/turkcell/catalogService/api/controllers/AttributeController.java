package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.AttributeService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedAttributeRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedAttributeRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedAttributeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/attributes")
public class AttributeController {

    private AttributeService attributeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAttributeResponse add(@RequestBody CreatedAttributeRequest createdAttributeRequest){
        return attributeService.add(createdAttributeRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAttributeResponse update(@RequestBody UpdatedAttributeRequest updatedAttributeRequest){
        return attributeService.update(updatedAttributeRequest);
    }
}
