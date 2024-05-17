package com.turkcell.CustomerService.dataAccess.abstracts;

import com.turkcell.CustomerService.entities.concretes.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = false WHERE c.id = :id")
    void softDelete(int id);

}
