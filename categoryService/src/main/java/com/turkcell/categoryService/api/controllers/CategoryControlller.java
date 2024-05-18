package com.turkcell.categoryService.api.controllers;

import com.turkcell.categoryService.business.abstacts.CategoryService;
import com.turkcell.categoryService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.categoryService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.categoryService.business.dtos.response.update.UpdatedCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/categoryservice/api/v1/categories")
public class CategoryControlller {

    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCategoryResponse add(@RequestBody CreatedCategoryRequest createdCategoryRequest){
        return categoryService.add(createdCategoryRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCategoryResponse update(@RequestBody UpdatedCategoryRequest updatedCategoryRequest){
        return categoryService.update(updatedCategoryRequest);
    }
}
