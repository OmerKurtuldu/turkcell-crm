package com.turkcell.categoryService.business.abstacts;

import com.turkcell.categoryService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedProductResponse;

public interface ProductService {

    CreatedProductResponse add(CreatedProductRequest createdProductRequest);
    UpdatedProductResponse update(UpdatedProductRequest updatedProductRequest);

}
