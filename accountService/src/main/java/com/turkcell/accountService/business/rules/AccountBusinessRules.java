package com.turkcell.accountService.business.rules;

import com.turkcell.accountService.api.client.CustomerClient;
import com.turkcell.accountService.business.messages.Messages;
import com.turkcell.accountService.dataAccess.abstracts.AccountRepository;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.AccountTypes;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountBusinessRules {
    private final AccountRepository accountRepository;
    private final MessageService messageService;
    private final CustomerClient customerClient;
    private final AccountTypesRepository accountTypesRepository;

    public void checkCustomerAvailabilityForAccount(int customerId){
       var response = customerClient.customerGetById(customerId);
       if(!response.isSuccess()){
            throw new BusinessException(messageService.getMessage(Messages.AccountErrors.CustomerRegistrationShouldBeExist));
       }
    }

    public void accountTypeShouldBeExists(int id){
        Optional<AccountTypes> accountTypes = accountTypesRepository.findById(id);
        if(accountTypes.isEmpty()){
            throw new BusinessException(messageService.getMessageWithArgs(Messages.AccountErrors.AccountTypeShouldBeExists));
        }
    }

}
