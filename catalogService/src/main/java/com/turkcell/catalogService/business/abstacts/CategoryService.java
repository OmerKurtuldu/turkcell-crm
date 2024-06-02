package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreatedCategoryResponse add(CreatedCategoryRequest createdCategoryRequest);

    UpdatedCategoryResponse update(UpdatedCategoryRequest updatedCategoryRequest);

    GetCategoryResponse getById(int id);

    List<GetAllCategoryResponse> getAll();

    void delete(int id);
}