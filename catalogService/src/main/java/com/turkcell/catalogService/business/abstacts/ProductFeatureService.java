package com.turkcell.catalogService.business.abstacts;

import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.entities.concretes.ProductFeature;

import java.util.List;

public interface ProductFeatureService {
    List<ProductFeatureResponse> updateFeatureForProduct(int productId, List<ProductFeatureRequest> productFeatureRequests);
    void saveAll(List<ProductFeature> productFeatures);
    List<ProductFeature>  findByProductId(int id);
}
