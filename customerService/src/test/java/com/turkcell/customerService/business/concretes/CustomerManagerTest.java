package com.turkcell.customerService.business.concretes;


import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.customerService.business.rules.CustomerBusinessRules;
import com.turkcell.customerService.dataAccess.abstracts.CustomerRepository;
import com.turkcell.customerService.entities.concretes.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerManagerTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerBusinessRules customerBusinessRules;

    @InjectMocks
    private CustomerManager customerManager;

    @Test
    void saveCustomerWhenSuccess() {
        // Given
        Customer customer = new Customer("omerkurtuldu11@gmail.com", "05075979691", true, null, null);

        // When
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Then
        Assertions.assertDoesNotThrow(() -> {
            customerManager.saveCustomer(customer);
        });
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void deleteCustomerWhenSuccess() {
        // When
        doNothing().when(customerRepository).softDelete(anyInt());

        // Then
        Assertions.assertDoesNotThrow(() -> {
            customerManager.deleteCustomer(1);
        });
        verify(customerRepository, times(1)).softDelete(anyInt());
    }

    @Test
    void setActiveCustomerWhenSuccess() {
        // When
        doNothing().when(customerRepository).setActiveCustomer(anyInt());

        // Then
        Assertions.assertDoesNotThrow(() -> {
            customerManager.setActiveCustomer(1);
        });
        verify(customerRepository, times(1)).setActiveCustomer(anyInt());
    }

    @Test
    void getByIdWhenSuccess() {
        // Given
        Customer customer = new Customer("omerkurtuldu11@gmail.com", "05075979691", true, null, null);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        // When
        doNothing().when(customerBusinessRules).customerShouldBeExist(anyInt());
        when(customerRepository.findById(anyInt())).thenReturn(optionalCustomer);

        // Then
        Customer foundCustomer = customerManager.getById(1);
        Assertions.assertNotNull(foundCustomer);
        Assertions.assertEquals(customer.getEmail(), foundCustomer.getEmail());
        verify(customerBusinessRules, times(1)).customerShouldBeExist(anyInt());
        verify(customerRepository, times(1)).findById(anyInt());
    }

    @Test
    void getByIdWhenCustomerDoesNotExist() {
        // When
        doThrow(BusinessException.class).when(customerBusinessRules).customerShouldBeExist(anyInt());

        // Then
        Assertions.assertThrows(BusinessException.class, () -> {
            customerManager.getById(1);
        });
        verify(customerBusinessRules, times(1)).customerShouldBeExist(anyInt());
    }
}