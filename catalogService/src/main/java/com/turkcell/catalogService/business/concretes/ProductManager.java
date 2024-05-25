package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductFeatureService;
import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;

import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;


import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import com.turkcell.catalogService.business.rules.CategoryBusinessRules;
import com.turkcell.catalogService.business.rules.ProductBusinessRules;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.dataAccess.abstracts.FeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductFeatureRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
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
    private final ProductFeatureService productFeatureService;
    private final ProductBusinessRules productBusinessRules;
    private final CategoryBusinessRules categoryBusinessRules;

    @Override
    public CreatedProductResponse addProduct(CreatedProductRequest createdProductRequest) {

        categoryBusinessRules.categoryShouldBeExist(createdProductRequest.getCategoryId());
        productBusinessRules.featureNameCanNotRepeat(createdProductRequest.getProductFeatures());

        Product product = this.modelMapperService.forRequest().map(createdProductRequest, Product.class);
        product.setId(0);
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

    @Override
    public UpdatedProductResponse updateProduct(UpdatedProductRequest updatedProductRequest) {

        productBusinessRules.productShouldBeExist(updatedProductRequest.getId());
        productBusinessRules.featureNameCanNotRepeat(updatedProductRequest.getProductFeatures());
        categoryBusinessRules.categoryShouldBeExist(updatedProductRequest.getCategoryDTO().getCategoryId());

        Product product = this.modelMapperService.forRequest().map(updatedProductRequest, Product.class);
        Product savedProduct = productRepository.save(product);

        List<ProductFeatureResponse> productFeatureResponses =  productFeatureService.updateFeatureForProduct(updatedProductRequest.getId(), updatedProductRequest.getProductFeatures());

        UpdatedProductResponse updatedProductResponse = this.modelMapperService.forResponse().map(savedProduct, UpdatedProductResponse.class);

        updatedProductResponse.setProductFeatures(productFeatureResponses);

        return updatedProductResponse;
    }

    @Override
    public GetProductResponse getById(int id) {
        productBusinessRules.productShouldBeExist(id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            // Hata durumu ele alınmalı
            throw new RuntimeException("Product not found");
        }
        Product product = productOptional.get();

        // Ürün özelliklerini al ve ProductFeatureResponse listesine dönüştür
        List<ProductFeatureResponse> productFeatureResponses = productFeatureRepository.findByProductId(id).stream()
                .map(productFeature -> {
                    ProductFeatureResponse response = new ProductFeatureResponse();
                    response.setValue(productFeature.getValue());
                    response.setFeatureName(productFeature.getFeature().getName());
                    return response;
                })
                .collect(Collectors.toList());

        // Ürünü GetProductResponse'ye dönüştür
        GetProductResponse getProductResponse = this.modelMapperService.forResponse().map(product, GetProductResponse.class);
        getProductResponse.setProductFeatures(productFeatureResponses);

        return getProductResponse;
    }

    @Override
    public List<GetAllProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<GetAllProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            List<ProductFeatureResponse> productFeatureResponses = productFeatureRepository.findByProductId(product.getId()).stream()
                    .map(productFeature -> {
                        ProductFeatureResponse response = new ProductFeatureResponse();
                        response.setValue(productFeature.getValue());
                        response.setFeatureName(productFeature.getFeature().getName());
                        return response;
                    })
                    .collect(Collectors.toList());

            GetAllProductResponse getProductResponse = this.modelMapperService.forResponse().map(product, GetAllProductResponse.class);
            getProductResponse.setProductFeatures(productFeatureResponses);
            responses.add(getProductResponse);
        }
        return responses;
    }

    @Override
    public void delete(int id) {
        productBusinessRules.productShouldBeExist(id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {

            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}


