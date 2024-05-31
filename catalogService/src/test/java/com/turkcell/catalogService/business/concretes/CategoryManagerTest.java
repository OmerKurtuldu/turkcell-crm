package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.dtos.request.create.CreatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.request.update.UpdatedCategoryRequest;
import com.turkcell.catalogService.business.dtos.response.create.CreatedCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.get.GetCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.getall.GetAllCategoryResponse;
import com.turkcell.catalogService.business.dtos.response.update.UpdatedCategoryResponse;
import com.turkcell.catalogService.business.rules.CategoryBusinessRules;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


//todo çalışmadı bunlar
@ExtendWith(MockitoExtension.class)
class CategoryManagerTest {

    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryBusinessRules categoryBusinessRules;
    @InjectMocks
    private CategoryManager categoryManager;

    private void modelMapperForRequest() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(any(CreatedCategoryRequest.class), eq(Category.class))).thenAnswer(invocation -> {
            CreatedCategoryRequest request = invocation.getArgument(0);
            Category category = new Category();
            category.setCategoryName(request.getName());
            return category;
        });
        when(modelMapper.map(any(UpdatedCategoryRequest.class), eq(Category.class))).thenAnswer(invocation -> {
            UpdatedCategoryRequest request = invocation.getArgument(0);
            Category category = new Category();
            category.setId(request.getId());
            category.setCategoryName(request.getName());
            return category;
        });
    }

    private void modelMapperForResponse() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(modelMapper.map(any(Category.class), eq(CreatedCategoryResponse.class))).thenAnswer(invocation -> {
            Category source = invocation.getArgument(0);
            CreatedCategoryResponse response = new CreatedCategoryResponse();
            response.setId(source.getId());
            response.setName(source.getCategoryName());
            return response;
        });
        when(modelMapper.map(any(Category.class), eq(UpdatedCategoryResponse.class))).thenAnswer(invocation -> {
            Category source = invocation.getArgument(0);
            UpdatedCategoryResponse response = new UpdatedCategoryResponse();
            response.setId(source.getId());
            response.setName(source.getCategoryName());
            return response;
        });
        when(modelMapper.map(any(Category.class), eq(GetCategoryResponse.class))).thenAnswer(invocation -> {
            Category source = invocation.getArgument(0);
            GetCategoryResponse response = new GetCategoryResponse();
            response.setId(source.getId());
            response.setCategoryName(source.getCategoryName());
            return response;
        });
        when(modelMapper.map(any(Category.class), eq(GetAllCategoryResponse.class))).thenAnswer(invocation -> {
            Category source = invocation.getArgument(0);
            GetAllCategoryResponse response = new GetAllCategoryResponse();
            response.setId(source.getId());
            response.setCategoryName(source.getCategoryName());
            return response;
        });
    }

    @Test
    void addCategoryWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        CreatedCategoryRequest createdCategoryRequest = new CreatedCategoryRequest();
        createdCategoryRequest.setName("Category1");

        Category category = new Category();
        category.setId(1);
        category.setCategoryName("Category1");

        CreatedCategoryResponse createdCategoryResponse = new CreatedCategoryResponse();
        createdCategoryResponse.setId(1);
        createdCategoryResponse.setName("Category1");

        when(modelMapperService.forRequest().map(any(CreatedCategoryRequest.class), eq(Category.class))).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(modelMapperService.forResponse().map(any(Category.class), eq(CreatedCategoryResponse.class))).thenReturn(createdCategoryResponse);

        // When
        CreatedCategoryResponse response = categoryManager.add(createdCategoryRequest);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Category1", response.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategoryWhenSuccess() {
        // Given
        modelMapperForRequest();
        modelMapperForResponse();

        UpdatedCategoryRequest updatedCategoryRequest = new UpdatedCategoryRequest();
        updatedCategoryRequest.setId(1);
        updatedCategoryRequest.setName("UpdatedCategory");

        Category category = new Category();
        category.setId(1);
        category.setCategoryName("UpdatedCategory");

        UpdatedCategoryResponse updatedCategoryResponse = new UpdatedCategoryResponse();
        updatedCategoryResponse.setId(1);
        updatedCategoryResponse.setName("UpdatedCategory");

        when(modelMapperService.forRequest().map(any(UpdatedCategoryRequest.class), eq(Category.class))).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(modelMapperService.forResponse().map(any(Category.class), eq(UpdatedCategoryResponse.class))).thenReturn(updatedCategoryResponse);

        // When
        UpdatedCategoryResponse response = categoryManager.update(updatedCategoryRequest);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("UpdatedCategory", response.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void getCategoryByIdWhenSuccess() {
        // Given
        modelMapperForResponse();

        Category category = new Category();
        category.setId(1);
        category.setCategoryName("Category1");

        GetCategoryResponse getCategoryResponse = new GetCategoryResponse();
        getCategoryResponse.setId(1);
        getCategoryResponse.setCategoryName("Category1");

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        when(modelMapperService.forResponse().map(any(Category.class), eq(GetCategoryResponse.class))).thenReturn(getCategoryResponse);

        // When
        GetCategoryResponse response = categoryManager.getById(1);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Category1", response.getCategoryName());
    }

    @Test
    void getAllCategoriesWhenSuccess() {
        // Given
        modelMapperForResponse();

        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setId(1);
        category1.setCategoryName("Category1");

        Category category2 = new Category();
        category2.setId(2);
        category2.setCategoryName("Category2");

        categories.add(category1);
        categories.add(category2);

        GetAllCategoryResponse getAllCategoryResponse1 = new GetAllCategoryResponse();
        getAllCategoryResponse1.setId(1);
        getAllCategoryResponse1.setCategoryName("Category1");

        GetAllCategoryResponse getAllCategoryResponse2 = new GetAllCategoryResponse();
        getAllCategoryResponse2.setId(2);
        getAllCategoryResponse2.setCategoryName("Category2");

        when(categoryRepository.findAll()).thenReturn(categories);
        when(modelMapperService.forResponse().map(any(Category.class), eq(GetAllCategoryResponse.class)))
                .thenReturn(getAllCategoryResponse1, getAllCategoryResponse2);

        // When
        List<GetAllCategoryResponse> responses = categoryManager.getAll();

        // Then
        Assertions.assertNotNull(responses);
        Assertions.assertEquals(2, responses.size());
        Assertions.assertEquals("Category1", responses.get(0).getCategoryName());
        Assertions.assertEquals("Category2", responses.get(1).getCategoryName());
    }

    @Test
    void deleteCategoryWhenSuccess() {
        // Given
        int categoryId = 1;

        // When
        categoryManager.delete(categoryId);

        // Then
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
