package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.FeatureService;
import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedFeatureResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedFeatureResponse update(@RequestBody UpdatedFeatureRequest updatedFeatureRequest){
        return featureService.updateFeature(updatedFeatureRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFeatureResponse getById(@PathVariable int id){
        return featureService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFeatureResponse> getAll(){
        return featureService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        featureService.delete(id);
    }

}
