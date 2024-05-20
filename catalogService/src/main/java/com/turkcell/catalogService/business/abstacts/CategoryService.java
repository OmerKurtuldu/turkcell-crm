package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;

public interface CategoryService {
    CreatedCategoryResponse add(CreatedCategoryRequest createdCategoryRequest);
    UpdatedCategoryResponse update(UpdatedCategoryRequest updatedCategoryRequest);
}
