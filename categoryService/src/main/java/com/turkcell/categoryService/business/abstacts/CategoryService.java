package com.turkcell.categoryService.business.abstacts;

import com.turkcell.categoryService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedCategoryResponse;

public interface CategoryService {
    CreatedCategoryResponse add(CreatedCategoryRequest createdCategoryRequest);
    UpdatedCategoryResponse update(UpdatedCategoryRequest updatedCategoryRequest);
}
