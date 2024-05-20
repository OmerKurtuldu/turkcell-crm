package com.turkcell.customerService.business.abstracts;

import com.turkcell.customerService.entities.concretes.Customer;

public interface CustomerService {
    void saveCustomer (Customer customer);
    void deleteCustomer (int id);
    void setActiveCustomer(int id);
    Customer getById(int id);

}
