package com.turkcell.accountService.business.rules;

import com.turkcell.accountService.business.messages.Messages;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.AccountType;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountTypeBusinessRules {
    private final AccountTypesRepository accountTypesRepository;
    private final MessageService messageService;


    public void accountTypeShouldBeExist(int accountTypeId) {
        Optional<AccountType> foundOptionalAccountType = accountTypesRepository.findById(accountTypeId);
        if (foundOptionalAccountType.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.AccountTypeErrors.AccountTypeShouldBeExists));
        }
    }
}
