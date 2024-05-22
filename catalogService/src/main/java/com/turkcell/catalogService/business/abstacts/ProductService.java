package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;

public interface ProductService {
    CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest);
}
