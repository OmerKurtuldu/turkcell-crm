package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductFeatureService;
import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductFeatureRepository;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.catalogService.entities.concretes.ProductFeature;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductFeatureManager implements ProductFeatureService {
    private final ModelMapperService modelMapperService;
    private final ProductFeatureRepository productFeatureRepository;
    private final FeatureRepository featureRepository;

    @Override
    public void updateFeatureForProduct(List<ProductFeatureRequest> productFeatureRequest) {
        for (ProductFeatureRequest featureRequest : productFeatureRequest){
            //todo : iş kuralı gelecek
            Optional<ProductFeature> productFeature = productFeatureRepository.findById(featureRequest.getFeatureId());
            productFeature.get().setValue(featureRequest.getValue());

            productFeatureRepository.save(productFeature.get());
        }
    }
}
