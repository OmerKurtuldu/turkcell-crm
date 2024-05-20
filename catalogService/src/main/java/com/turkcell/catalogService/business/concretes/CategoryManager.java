package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.CategoryService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryManager implements CategoryService {

    private final ModelMapperService modelMapperService;
    private final CategoryRepository categoryRepository;

    @Override
    public CreatedCategoryResponse add(CreatedCategoryRequest createdCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(createdCategoryRequest,Category.class);
        categoryRepository.save(category);
        return this.modelMapperService.forResponse().map(category,CreatedCategoryResponse.class);
    }

    @Override
    public UpdatedCategoryResponse update(UpdatedCategoryRequest updatedCategoryRequest) {
        //todo iş kuralı eklenecek
        Category category = this.modelMapperService.forRequest().map(updatedCategoryRequest,Category.class);
        categoryRepository.save(category);
        return this.modelMapperService.forResponse().map(category,UpdatedCategoryResponse.class);
    }
}
