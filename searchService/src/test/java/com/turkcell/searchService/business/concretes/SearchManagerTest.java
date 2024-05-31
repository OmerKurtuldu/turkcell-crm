package com.turkcell.searchService.business.concretes;

import com.turkcell.searchService.entities.concretes.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchManagerTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private SearchManager searchManager;

    @Test
    void searchCustomerWhenAllFieldsAreNull() {
        // Given
        Customer customerSearch = new Customer();

        // When
        when(mongoOperations.find(any(Query.class), eq(Customer.class))).thenReturn(List.of());

        // Then
        List<Customer> result = searchManager.searchCustomer(customerSearch);
        Assertions.assertTrue(result.isEmpty());
        verify(mongoOperations, times(1)).find(any(Query.class), eq(Customer.class));
    }

    @Test
    void searchCustomerWhenSomeFieldsAreNotNull() {
        // Given
        Customer customerSearch = new Customer();
        customerSearch.setFirstName("Ahmet");
        customerSearch.setLastName("Yılmaz");

        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("firstName").regex("Ahmet", "i"));
        expectedQuery.addCriteria(Criteria.where("lastName").regex("Yılmaz", "i"));

        Customer foundCustomer = new Customer();
        foundCustomer.setFirstName("Ahmet");
        foundCustomer.setLastName("Yılmaz");

        // When
        when(mongoOperations.find(any(Query.class), eq(Customer.class))).thenReturn(List.of(foundCustomer));

        // Then
        List<Customer> result = searchManager.searchCustomer(customerSearch);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Ahmet", result.get(0).getFirstName());
        Assertions.assertEquals("Yılmaz", result.get(0).getLastName());
        verify(mongoOperations, times(1)).find(any(Query.class), eq(Customer.class));
    }

    @Test
    void searchCustomerWhenAllFieldsAreNotNull() {
        // Given
        Customer customerSearch = new Customer();
        customerSearch.setId("123");
        customerSearch.setFirstName("Mehmet");
        customerSearch.setLastName("Demir");
        customerSearch.setNationalityNumber("12345678910");
        customerSearch.setMobilePhone("5551234567");

        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("id").regex("123", "i"));
        expectedQuery.addCriteria(Criteria.where("firstName").regex("Mehmet", "i"));
        expectedQuery.addCriteria(Criteria.where("lastName").regex("Demir", "i"));
        expectedQuery.addCriteria(Criteria.where("nationalityNumber").regex("12345678910", "i"));
        expectedQuery.addCriteria(Criteria.where("mobilePhone").regex("5551234567", "i"));

        Customer foundCustomer = new Customer();
        foundCustomer.setId("123");
        foundCustomer.setFirstName("Mehmet");
        foundCustomer.setLastName("Demir");
        foundCustomer.setNationalityNumber("12345678910");
        foundCustomer.setMobilePhone("5551234567");

        // When
        when(mongoOperations.find(any(Query.class), eq(Customer.class))).thenReturn(List.of(foundCustomer));

        // Then
        List<Customer> result = searchManager.searchCustomer(customerSearch);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("123", result.get(0).getId());
        Assertions.assertEquals("Mehmet", result.get(0).getFirstName());
        Assertions.assertEquals("Demir", result.get(0).getLastName());
        Assertions.assertEquals("12345678910", result.get(0).getNationalityNumber());
        Assertions.assertEquals("5551234567", result.get(0).getMobilePhone());
        verify(mongoOperations, times(1)).find(any(Query.class), eq(Customer.class));
    }
}
