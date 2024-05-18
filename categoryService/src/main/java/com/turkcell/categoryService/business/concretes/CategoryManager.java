package com.turkcell.categoryService.business.concretes;

import com.turkcell.categoryService.business.abstacts.CategoryService;
import com.turkcell.categoryService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedCategoryResponse;
import com.turkcell.categoryService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.categoryService.entities.concretes.Category;
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
