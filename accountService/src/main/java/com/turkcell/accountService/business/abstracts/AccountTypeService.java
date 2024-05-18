package com.turkcell.accountService.business.abstracts;

import com.turkcell.accountService.entities.concretes.AccountType;

public interface AccountTypeService {
    AccountType getById(int id);
}
