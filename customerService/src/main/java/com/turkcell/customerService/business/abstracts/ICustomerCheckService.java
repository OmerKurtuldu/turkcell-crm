package com.turkcell.customerService.business.abstracts;

import com.turkcell.customerService.entities.concretes.IndividualCustomer;

public interface ICustomerCheckService {

    boolean checkIfRealPerson(IndividualCustomer individualCustomer);
}
