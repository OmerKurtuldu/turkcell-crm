package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedAttributeRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedAttributeRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedAttributeResponse;

public interface AttributeService {

    CreatedAttributeResponse add(CreatedAttributeRequest createdAttributeRequest);
    UpdatedAttributeResponse update(UpdatedAttributeRequest updatedAttributeRequest);
}
