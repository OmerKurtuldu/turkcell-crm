package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
