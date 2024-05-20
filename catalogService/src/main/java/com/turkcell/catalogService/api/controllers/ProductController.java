package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/products")
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@RequestBody CreatedProductRequest createdProductRequest){
        return productService.add(createdProductRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@RequestBody UpdatedProductRequest updatedProductRequest){
        return productService.update(updatedProductRequest);
    }
}
