package com.turkcell.customerService.dataAccess.abstracts;

import com.turkcell.customerService.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
