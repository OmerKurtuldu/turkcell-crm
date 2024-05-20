package com.turkcell.customerService.dataAccess.abstracts;

import com.turkcell.customerService.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer> {
    Optional<IndividualCustomer> findByNationalityNo(String nationalityNo);
}
