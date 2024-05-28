package com.turkcell.basketService.business.rules;

import com.turkcell.basketService.api.client.AccountServiceClient;
import com.turkcell.basketService.business.messages.Messages;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketBusinessRules {
    private final MessageService messageService;
    private final AccountServiceClient accountServiceClient;

    public void checkAccountAvailabilityForBasket(String accountId){

        ClientResponse response = accountServiceClient.accountGetById(Integer.parseInt(accountId));
        if(!response.isSuccess()){
            throw new BusinessException(messageService.getMessage(Messages.BasketErrors.AccountRegistrationShouldBeExist));
        }
    }

}
