package com.turkcell.customerService.dataAccess.abstracts;

import com.turkcell.customerService.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer> {
    Optional<IndividualCustomer> findByNationalityNo(String nationalityNo);

    @Query("""
            SELECT ic FROM IndividualCustomer ic 
            JOIN FETCH ic.customer c
            WHERE c.isActive = true
            """)
    List<IndividualCustomer> findAllActiveIndividualCustomers();

    @Query("""
            SELECT ic FROM IndividualCustomer ic 
            JOIN FETCH ic.customer c
            WHERE c.isActive = true
            AND ic.id = :id
            """)
    Optional<IndividualCustomer> findByIdActiveIndividualCustomers(int id);
}
