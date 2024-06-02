package com.turkcell.catalogService.api.controllers;

import com.turkcell.catalogService.business.abstacts.CategoryService;
import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalogservice/api/v1/categories")
public class CategoryControlller {

    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCategoryResponse add(@RequestBody CreatedCategoryRequest createdCategoryRequest) {
        return categoryService.add(createdCategoryRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCategoryResponse update(@RequestBody UpdatedCategoryRequest updatedCategoryRequest) {
        return categoryService.update(updatedCategoryRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCategoryResponse getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
