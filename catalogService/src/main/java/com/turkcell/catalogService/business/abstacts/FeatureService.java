package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedFeatureResponse;

import java.util.List;

public interface FeatureService {
    CreatedFeatureResponse addFeature(CreatedFeatureRequest createdFeatureRequest);
    UpdatedFeatureResponse updateFeature(UpdatedFeatureRequest updatedFeatureRequest);
    GetFeatureResponse getById(int id);
    List<GetAllFeatureResponse> getAll();
    void delete(int id);

}
