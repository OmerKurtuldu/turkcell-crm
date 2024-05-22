package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;

import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;


import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductFeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.catalogService.entities.concretes.Feature;
import com.turkcell.catalogService.entities.concretes.Product;
import com.turkcell.catalogService.entities.concretes.ProductFeature;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductManager implements ProductService {

    private final ModelMapperService modelMapperService;
    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ProductFeatureRepository productFeatureRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest) {
        //todo aynı feature id den eklenemesin iş kuralı
        Product product = this.modelMapperService.forRequest().map(createdProductRequest, Product.class);
        Product savedProduct = productRepository.save(product);

        List<ProductFeature> productFeatures = new ArrayList<>();
        for (ProductFeatureRequest featureRequest : createdProductRequest.getProductFeatures()) {
            Feature feature = featureRepository.findById(featureRequest.getFeatureId())
                    .orElse(null);
            ProductFeature productFeature = new ProductFeature();
            productFeature.setProduct(savedProduct);
            productFeature.setFeature(feature);
            productFeature.setValue(featureRequest.getValue());
            productFeatures.add(productFeature);
        }
        productFeatureRepository.saveAll(productFeatures);

        List<ProductFeatureResponse> productFeatureResponses = productFeatures.stream()
                .map(productFeature -> this.modelMapperService.forResponse().map(productFeature, ProductFeatureResponse.class))
                .collect(Collectors.toList());

        CreatedProductResponse createdProductResponse = this.modelMapperService.forResponse().map(savedProduct, CreatedProductResponse.class);
        createdProductResponse.setProductFeatures(productFeatureResponses);

        return createdProductResponse;
    }
}
