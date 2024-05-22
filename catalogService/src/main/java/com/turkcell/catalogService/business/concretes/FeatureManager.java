package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.FeatureService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeatureManager implements FeatureService {
    private final ModelMapperService modelMapperService;
    private final FeatureRepository featureRepository;

    @Override
    public CreatedFeatureResponse addFeature(CreatedFeatureRequest createdFeatureRequest) {
        Feature feature = this.modelMapperService.forRequest().map(createdFeatureRequest, Feature.class);
        featureRepository.save(feature);
        return this.modelMapperService.forResponse().map(feature, CreatedFeatureResponse.class);
    }
}
