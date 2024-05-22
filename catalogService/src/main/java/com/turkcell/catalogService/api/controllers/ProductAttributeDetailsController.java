package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.ProductAttributeDetailsService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductAttributeDetailsRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductAttributeDetailsRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductAttributeDetailsResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductAttributeDetailsResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductAttributeDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/productAttributeDetails")
public class ProductAttributeDetailsController {

    private ProductAttributeDetailsService productAttributeDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductAttributeDetailsResponse add(@RequestBody CreatedProductAttributeDetailsRequest createdProductAttributeDetailsRequest){
        return productAttributeDetailsService.add(createdProductAttributeDetailsRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductAttributeDetailsResponse update(@RequestBody UpdatedProductAttributeDetailsRequest updatedProductAttributeDetailsRequest){
        return productAttributeDetailsService.update(updatedProductAttributeDetailsRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductAttributeDetailsResponse getById(@PathVariable int id){
        return productAttributeDetailsService.getById(id);
    }

}
