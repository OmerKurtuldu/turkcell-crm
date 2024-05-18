package com.turkcell.categoryService.api.controllers;

import com.turkcell.categoryService.business.abstacts.ProductAttributeDetailsService;
import com.turkcell.categoryService.business.dtos.request.create.CreatedProductAttributeDetailsRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedProductAttributeDetailsRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedProductAttributeDetailsResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedProductAttributeDetailsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/categoryservice/api/v1/productAttributeDetails")
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
}
