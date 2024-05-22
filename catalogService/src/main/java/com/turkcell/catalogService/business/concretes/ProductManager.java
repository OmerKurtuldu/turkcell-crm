package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductAttributeDetailsService;
import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetProductAttributeDetailsResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import com.turkcell.catalogService.dataAccess.abstracts.AttributeRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductAttributeDetailsRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
import com.turkcell.catalogService.entities.concretes.Attribute;
import com.turkcell.catalogService.entities.concretes.Product;
import com.turkcell.catalogService.entities.concretes.ProductAttributeDetails;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductManager implements ProductService {

    private final ModelMapperService modelMapperService;
    private final ProductRepository productRepository;
    private final ProductAttributeDetailsService productAttributeDetailsService;
    private final AttributeRepository attributeRepository;
    private final ProductAttributeDetailsRepository productAttributeDetailsRepository;
    private final MessageService messageService;

    @Override
    public CreatedProductResponse add(CreatedProductRequest createdProductRequest) {
        //todo category var mı yok mu kontrol edilicek.

        Product product = this.modelMapperService.forRequest().map(createdProductRequest,Product.class);
        List<GetProductAttributeDetailsResponse> getProductAttributeDetailsResponses = new ArrayList<>();
        for (int details : createdProductRequest.getAttributeDetailsId()){
            GetProductAttributeDetailsResponse getProductAttributeDetailsResponse = productAttributeDetailsService.getById(details);
            getProductAttributeDetailsResponses.add(getProductAttributeDetailsResponse);
        };

        productRepository.save(product);
        CreatedProductResponse createdProductResponse = this.modelMapperService.forResponse().map(product,CreatedProductResponse.class);
        createdProductResponse.setAttributes(getProductAttributeDetailsResponses);
        return createdProductResponse;
    }

    @Override
    public UpdatedProductResponse update(UpdatedProductRequest updatedProductRequest) {
        //todo category var mı yok mu kontrol edilicek.
        //todo böyle bir product var mı.
        Product product = this.modelMapperService.forRequest().map(updatedProductRequest,Product.class);
        List<ProductAttributeDetails> productAttributeDetails = updatedProductRequest.getAttributeDetailsId().stream()
                .map(attributeDetailsId -> productAttributeDetailsRepository.findById(attributeDetailsId)
                        .orElseThrow(() -> new IllegalArgumentException("Attribute not found")))
                .collect(Collectors.toList());
        product.setProductAttributeDetails(productAttributeDetails);
        productRepository.save(product);
        return this.modelMapperService.forResponse().map(product,UpdatedProductResponse.class);
    }
}
