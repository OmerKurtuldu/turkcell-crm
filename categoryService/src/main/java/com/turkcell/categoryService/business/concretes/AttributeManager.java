package com.turkcell.categoryService.business.concretes;

import com.turkcell.categoryService.business.abstacts.AttributeService;
import com.turkcell.categoryService.business.dtos.request.create.CreatedAttributeRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedAttributeRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedAttributeResponse;
import com.turkcell.categoryService.dataAccess.abstracts.AttributeRepository;
import com.turkcell.categoryService.entities.concretes.Attribute;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttributeManager implements AttributeService {

    private final ModelMapperService modelMapperService;
    private final AttributeRepository attributeRepository;

    @Override
    public CreatedAttributeResponse add(CreatedAttributeRequest createdAttributeRequest) {
        Attribute attribute = this.modelMapperService.forRequest().map(createdAttributeRequest,Attribute.class);

        attributeRepository.save(attribute);

        return this.modelMapperService.forResponse().map(attribute,CreatedAttributeResponse.class);
    }

    @Override
    public UpdatedAttributeResponse update(UpdatedAttributeRequest updatedAttributeRequest) {
        Attribute attribute = this.modelMapperService.forRequest().map(updatedAttributeRequest,Attribute.class);
        attributeRepository.save(attribute);
        return this.modelMapperService.forResponse().map(attribute,UpdatedAttributeResponse.class);
    }
}
