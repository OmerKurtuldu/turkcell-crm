package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductFeatureService;
import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.dataAccess.abstracts.ProductFeatureRepository;
import com.turkcell.catalogService.entities.concretes.ProductFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductFeatureManager implements ProductFeatureService {

    private final ProductFeatureRepository productFeatureRepository;

    public List<ProductFeatureResponse> updateFeatureForProduct(int productId, List<ProductFeatureRequest> productFeatureRequests) {
        List<ProductFeature> existingProductFeatures = productFeatureRepository.findByProductId(productId);

        List<ProductFeatureResponse> productFeatureResponses = new ArrayList<ProductFeatureResponse>();
        for (ProductFeatureRequest featureRequest : productFeatureRequests) {
            for (ProductFeature existingFeature : existingProductFeatures) {
                if (existingFeature.getFeature().getId() == featureRequest.getFeatureId()) {
                    existingFeature.setValue(featureRequest.getValue());
                    productFeatureRepository.save(existingFeature);
                }
            }
            productFeatureResponses = productFeatureRepository.findByProductId(productId).stream()
                    .map(productFeature -> {
                        ProductFeatureResponse response = new ProductFeatureResponse();
                        response.setValue(productFeature.getValue());
                        response.setFeatureName(productFeature.getFeature().getName());
                        return response;
                    })
                    .collect(Collectors.toList());

        }
        return productFeatureResponses;
    }

    @Override
    public void saveAll(List<ProductFeature> productFeatures) {
        productFeatureRepository.saveAll(productFeatures);
    }

    @Override
    public List<ProductFeature> findByProductId(int id) {
        List<ProductFeature> productFeatureResponses = productFeatureRepository.findByProductId(id);
        return productFeatureResponses;
    }
}
