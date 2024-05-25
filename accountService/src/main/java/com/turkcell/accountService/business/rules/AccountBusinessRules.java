package com.turkcell.accountService.business.rules;

import com.turkcell.accountService.api.client.CustomerServiceClient;
import com.turkcell.accountService.business.messages.Messages;
import com.turkcell.accountService.dataAccess.abstracts.AccountRepository;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.Account;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountBusinessRules {
    private final AccountRepository accountRepository;
    private final MessageService messageService;
    private final CustomerServiceClient customerServiceClient;


    public void checkCustomerAvailabilityForAccount(int customerId){

       var response = customerServiceClient.customerGetById(customerId);
       if(!response.isSuccess()){
            throw new BusinessException(messageService.getMessage(Messages.AccountCustomerErrors.CustomerRegistrationShouldBeExist));
       }
    }

    public void checkAddressAvailabilityForAccount(List<Integer> addressIds){
        for (int addressId : addressIds){
            var response = customerServiceClient.addressGetById(addressId);
            if(!response.isSuccess()){
                throw new BusinessException(messageService.getMessage(Messages.AccountAddressErrors.AdressRegistrationShouldBeExist));
            }
        }

    }

//    public void accountTypeShouldBeExists(int id){
//        Optional<AccountTypes> accountTypes = accountTypesRepository.findById(id);
//        if(accountTypes.isEmpty()){
//            throw new BusinessException(messageService.getMessageWithArgs(Messages.AccountErrors.AccountTypeShouldBeExists));
//        }
//    }

    public void accountShoulBeExist(int accountId){
        Optional<Account> foundOptionalAccount = accountRepository.findById(accountId);
        if (foundOptionalAccount.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.AccountErrors.AccountShouldBeExist));
        }
    }

}
