package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
