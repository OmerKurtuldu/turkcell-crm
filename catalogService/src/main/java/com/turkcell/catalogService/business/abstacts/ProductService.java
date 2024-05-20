package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;

public interface ProductService {

    CreatedProductResponse add(CreatedProductRequest createdProductRequest);
    UpdatedProductResponse update(UpdatedProductRequest updatedProductRequest);

}
