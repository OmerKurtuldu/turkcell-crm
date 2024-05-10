package com.turkcell.searchService.business.abstracts;

import com.turkcell.searchService.entities.concretes.Customer;

import java.util.List;

public interface SearchService {
    List<Customer> searchCustomer(Customer customerSearch);
}
