package com.turkcell.CustomerService.business.rules;

import com.turkcell.CustomerService.business.messages.Messages;
import com.turkcell.CustomerService.core.business.abstracts.MessageService;
import com.turkcell.CustomerService.core.crossCuttingConcerns.exceptions.types.BusinessException;
import com.turkcell.CustomerService.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.CustomerService.entities.concretes.IndividualCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IndividualCustomerBusinessRules {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final MessageService messageService;

    public void individualCustomerShouldBeExist(int individualCustomerId) {
        Optional<IndividualCustomer> foundOptionalIndividualCustomer = individualCustomerRepository.findById(individualCustomerId);
        if (foundOptionalIndividualCustomer.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerErrors.IndividualCustomerShouldBeExists));
        }
    }

    public void nationalityNoCanNotBeDuplicated(String nationalityNo ){
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findByNationalityNo(nationalityNo);
        if(individualCustomer.isPresent()){
            throw new BusinessException(messageService.getMessage((Messages.CustomerErrors.IndividualCustomerWithThisIDNumberExist)));
        }
    }



}
