package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;

import java.util.List;

public interface ProductFeatureService {
    void updateFeatureForProduct( List<ProductFeatureRequest> productFeatureRequest);
}
