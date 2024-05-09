package com.turkcell.CustomerService.dataAccess.abstracts;

import com.turkcell.CustomerService.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer> {
    Optional<IndividualCustomer> findByNationalityNo(String nationalityNo);
}
