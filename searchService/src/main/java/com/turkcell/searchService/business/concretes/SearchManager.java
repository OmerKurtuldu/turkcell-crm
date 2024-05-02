package com.turkcell.searchService.business.concretes;

import com.turkcell.searchService.business.abstracts.SearchService;
import com.turkcell.searchService.entities.Customer;
import com.turkcell.searchService.repositories.CustomerRepository;
import com.turkcell.searchService.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {

    private SearchRepository searchRepository;
    private CustomerRepository customerRepository;
    @Override
    public void add(Customer customer) {
        this.customerRepository.save(customer);
    }
}
