package com.turkcell.catalogService.business.concretes;

import com.turkcell.catalogService.business.rules.CategoryBusinessRules;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


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
