package com.turkcell.catalogService.business.rules;

import com.turkcell.catalogService.business.messages.Messages;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FeatureBusinessRules {
    private final MessageService messageService;
    private final FeatureRepository featureRepository;

    public void featureNameCanNotBeDuplicated(String name){
        Optional<Feature> feature = featureRepository.findByName(name);
        if(feature.isPresent()){
            throw new BusinessException(messageService.getMessage(Messages.FeatureErrors.FeatureNameCanNotBeDuplicated));
        }
    }

    public void featureShouldBeExist (int featureId){
        Optional<Feature> feature = featureRepository.findById(featureId);
        if(feature.isEmpty()){
            throw new BusinessException(messageService.getMessageWithArgs(Messages.CategoryErrors.CategoryShouldBeExist));
        }
    }

}
