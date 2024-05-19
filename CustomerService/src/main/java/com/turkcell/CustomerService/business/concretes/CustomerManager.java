package com.turkcell.CustomerService.business.concretes;

import com.turkcell.CustomerService.business.abstracts.CustomerService;
import com.turkcell.CustomerService.business.rules.CustomerBusinessRules;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Customer;
import com.turkcell.corepackage.business.abstracts.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
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
