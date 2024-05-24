package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;

import java.util.List;

public interface ProductService {
    CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest);
    UpdatedProductResponse updateProduct(UpdatedProductRequest updatedProductRequest);
    GetProductResponse getById(int id);
    List<GetAllProductResponse> getAll();
    void delete(int id);
}
