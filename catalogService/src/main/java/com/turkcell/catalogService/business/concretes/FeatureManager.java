package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.FeatureService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedFeatureResponse;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public UpdatedFeatureResponse updateFeature(UpdatedFeatureRequest updatedFeatureRequest) {
        Feature feature = this.modelMapperService.forRequest().map(updatedFeatureRequest, Feature.class);
        featureRepository.save(feature);
        return this.modelMapperService.forResponse().map(feature, UpdatedFeatureResponse.class);
    }

    @Override
    public GetFeatureResponse getById(int id) {
        Optional<Feature> feature = featureRepository.findById(id);
        return this.modelMapperService.forResponse().map(feature.get(),GetFeatureResponse.class);
    }

    @Override
    public List<GetAllFeatureResponse> getAll() {
        List<Feature> features = featureRepository.findAll();
        List<GetAllFeatureResponse> getAllFeatureResponses = new ArrayList<>();
        for(var feature : features){
            GetAllFeatureResponse getAllFeatureResponse = this.modelMapperService.forResponse().map(feature,GetAllFeatureResponse.class);
            getAllFeatureResponses.add(getAllFeatureResponse);
        }
        return getAllFeatureResponses;
    }

    @Override
    public void delete(int id) {
        featureRepository.deleteById(id);
    }
}
