package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;

public interface FeatureService {
    CreatedFeatureResponse addFeature(CreatedFeatureRequest createdFeatureRequest);

}
