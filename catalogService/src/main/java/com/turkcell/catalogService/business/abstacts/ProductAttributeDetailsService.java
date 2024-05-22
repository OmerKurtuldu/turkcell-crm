package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedProductAttributeDetailsRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductAttributeDetailsRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductAttributeDetailsResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductAttributeDetailsResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductAttributeDetailsResponse;

public interface ProductAttributeDetailsService {

    CreatedProductAttributeDetailsResponse add(CreatedProductAttributeDetailsRequest createdProductAttributeDetailsRequest);
    UpdatedProductAttributeDetailsResponse update(UpdatedProductAttributeDetailsRequest updatedProductAttributeDetailsRequest);
    GetProductAttributeDetailsResponse getById(int id);

}
