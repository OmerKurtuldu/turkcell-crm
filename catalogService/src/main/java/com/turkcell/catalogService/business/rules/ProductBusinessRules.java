package com.turkcell.catalogService.business.rules;

import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.messages.Messages;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
import com.turkcell.catalogService.entities.concretes.Product;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductBusinessRules {
    private final MessageService messageService;
    private final ProductRepository productRepository;

    public void featureNameCanNotRepeat(List<ProductFeatureRequest> productFeatures){
        Set<Integer> featureIds = new HashSet<>();
        for(ProductFeatureRequest feature : productFeatures){
            if (!featureIds.add(feature.getFeatureId())) {
                throw new BusinessException(messageService.getMessage(Messages.ProductErrors.FeatureNameCanNotRepeat));
            }
        }
    }

    public void productShouldBeExist(int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.ProductErrors.ProductShouldBeExist));
        }
    }
}
