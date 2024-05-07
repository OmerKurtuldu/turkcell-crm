package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypesRepository extends JpaRepository<AccountTypes,Integer> {
}
