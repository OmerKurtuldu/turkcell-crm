package com.turkcell.categoryService.business.abstacts;

import com.turkcell.categoryService.business.dtos.request.create.CreatedAttributeRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedAttributeRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedAttributeResponse;

public interface AttributeService {

    CreatedAttributeResponse add(CreatedAttributeRequest createdAttributeRequest);
    UpdatedAttributeResponse update(UpdatedAttributeRequest updatedAttributeRequest);
}
