package com.turkcell.CustomerService.dataAccess.abstracts;

import com.turkcell.CustomerService.entities.concretes.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = false WHERE c.id = :id")
    void softDelete(int id);

//    @Query("SELECT isActive FROM Customer WHERE Customer.id = :id")
//    boolean isActiveCustomer(int id);
    @Query("SELECT CASE WHEN c.isActive = true THEN true ELSE false END FROM Customer c WHERE c.id = :id")
    Boolean isActiveCustomer(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.isActive = true WHERE c.id = :id")
    void setActiveCustomer(int id);
}
