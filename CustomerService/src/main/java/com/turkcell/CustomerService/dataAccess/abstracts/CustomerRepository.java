package com.turkcell.CustomerService.dataAccess.abstracts;

import com.turkcell.CustomerService.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
