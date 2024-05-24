package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.abstacts.CategoryService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public GetCategoryResponse getById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return this.modelMapperService.forResponse().map(category.get(),GetCategoryResponse.class);
    }

    @Override
    public List<GetAllCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<GetAllCategoryResponse> getAllCategoryResponses = new ArrayList<>();
        for(var category : categories) {
            GetAllCategoryResponse getAllCategoryResponse = this.modelMapperService.forResponse().map(category,GetAllCategoryResponse.class);
            getAllCategoryResponses.add(getAllCategoryResponse);
        }
        return getAllCategoryResponses;
    }

    @Override
    public void delete(int id) {
     categoryRepository.deleteById(id);
    }

}
