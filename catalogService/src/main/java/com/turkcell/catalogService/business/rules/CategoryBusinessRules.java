package com.turkcell.catalogService.business.rules;

import com.turkcell.catalogService.business.abstacts.ProductService;
import com.turkcell.catalogService.business.messages.Messages;
import com.turkcell.catalogService.dataAccess.abstracts.CategoryRepository;
import com.turkcell.catalogService.entities.concretes.Category;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryBusinessRules {
    private final MessageService messageService;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public void categoryShouldBeExist (int categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new BusinessException(messageService.getMessageWithArgs(Messages.CategoryErrors.CategoryShouldBeExist));
        }
    }





}
