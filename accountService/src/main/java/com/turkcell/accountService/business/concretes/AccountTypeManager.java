package com.turkcell.accountService.business.concretes;

import com.turkcell.accountService.business.abstracts.AccountTypeService;
import com.turkcell.accountService.business.rules.AccountTypeBusinessRules;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTypeManager implements AccountTypeService {
    private final AccountTypesRepository accountTypesRepository;
    private final AccountTypeBusinessRules accountTypeBusinessRules;


    @Override
    public AccountType getById(int id) {
        accountTypeBusinessRules.accountTypeShouldBeExist(id);
        Optional<AccountType> accountType = accountTypesRepository.findById(id);
        return accountType.get();
    }
}
