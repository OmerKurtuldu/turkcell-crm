package com.turkcell.customerService.business.concretes;

import com.turkcell.customerService.business.abstracts.CustomerService;
import com.turkcell.customerService.business.rules.CustomerBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.customerService.entities.concretes.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.softDelete(id);
    }

    @Override
    public void setActiveCustomer(int id) {
        customerRepository.setActiveCustomer(id);
    }

    @Override
    public Customer getById(int id) {
        customerBusinessRules.customerShouldBeExist(id);
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }
}
