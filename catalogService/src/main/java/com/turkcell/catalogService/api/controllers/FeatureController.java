package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.FeatureService;
import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/features")
public class FeatureController {

    private FeatureService featureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFeatureResponse createProduct(@RequestBody CreatedFeatureRequest createdFeatureRequest) {
        return featureService.addFeature(createdFeatureRequest);
    }
}
