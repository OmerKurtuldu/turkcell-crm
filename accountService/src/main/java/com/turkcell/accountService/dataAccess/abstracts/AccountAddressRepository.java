package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.AccountAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAddressRepository extends JpaRepository<AccountAddresses,Integer> {
}
