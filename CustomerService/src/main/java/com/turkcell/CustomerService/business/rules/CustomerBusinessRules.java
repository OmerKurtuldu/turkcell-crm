package com.turkcell.CustomerService.business.rules;

import com.turkcell.CustomerService.business.messages.Messages;
import com.turkcell.CustomerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.CustomerService.entities.concretes.Customer;
import com.turkcell.CustomerService.entities.concretes.IndividualCustomer;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;


    public void customerShouldBeExist(int customerId) {
        Optional<Customer> foundOptionalCustomer = customerRepository.findById(customerId);
        if (foundOptionalCustomer.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.CustomerErrors.CustomerShouldBeExists));
        }
    }

}
