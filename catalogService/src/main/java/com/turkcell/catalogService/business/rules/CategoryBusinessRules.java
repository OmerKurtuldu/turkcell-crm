package com.turkcell.catalogService.business.rules;

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

    public void categoryShouldBeExist (int categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new BusinessException(messageService.getMessageWithArgs(Messages.CategoryErrors.CategoryShouldBeExist));
        }
    }

    public void categoryNameCanNotBeDuplicated(String name){
        Optional<Category> category = categoryRepository.findByCategoryName(name);
        if(category.isPresent()){
            throw new BusinessException(messageService.getMessage(Messages.CategoryErrors.CategoryNameCanNotBeDuplicated));
        }
    }





}
