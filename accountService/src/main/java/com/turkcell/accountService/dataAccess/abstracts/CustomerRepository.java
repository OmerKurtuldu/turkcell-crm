package com.turkcell.accountService.dataAccess.abstracts;

import com.turkcell.accountService.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
