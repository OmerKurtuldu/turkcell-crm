package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedProductRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedProductRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedProductResponse;
import com.turkcell.catalogService.dataAccess.abstracts.AttributeRepository;
import com.turkcell.catalogService.dataAccess.abstracts.ProductRepository;
import com.turkcell.catalogService.entities.concretes.Attribute;
import com.turkcell.catalogService.entities.concretes.Product;
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
    private final AttributeRepository attributeRepository;
    private final MessageService messageService;

    @Override
    public CreatedProductResponse add(CreatedProductRequest createdProductRequest) {
        //todo category var mı yok mu kontrol edilicek.
        Product product = this.modelMapperService.forRequest().map(createdProductRequest,Product.class);
        List<Attribute> attributes = createdProductRequest.getAttributeId().stream()
                .map(attributeId -> attributeRepository.findById(attributeId)
                        .orElseThrow(() -> new IllegalArgumentException("Attribute not found")))
                .collect(Collectors.toList());
        product.setAttributes(attributes);
        productRepository.save(product);
        return this.modelMapperService.forResponse().map(product,CreatedProductResponse.class);
    }

    @Override
    public UpdatedProductResponse update(UpdatedProductRequest updatedProductRequest) {
        //todo category var mı yok mu kontrol edilicek.
        //todo böyle bir product var mı.
        Product product = this.modelMapperService.forRequest().map(updatedProductRequest,Product.class);
        List<Attribute> attributes = updatedProductRequest.getAttributeId().stream()
                .map(attributeId -> attributeRepository.findById(attributeId)
                        .orElseThrow(() -> new IllegalArgumentException("Attribute not found")))
                .collect(Collectors.toList());
        product.setAttributes(attributes);
        productRepository.save(product);
        return this.modelMapperService.forResponse().map(product,UpdatedProductResponse.class);
    }
}
