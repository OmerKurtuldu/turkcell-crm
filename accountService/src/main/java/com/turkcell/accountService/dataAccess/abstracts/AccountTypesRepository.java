package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypesRepository extends JpaRepository<AccountType, Integer> {
}
