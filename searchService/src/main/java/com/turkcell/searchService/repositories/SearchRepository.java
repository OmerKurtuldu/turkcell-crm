package com.turkcell.searchService.repositories;

import com.turkcell.searchService.entities.concretes.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchRepository extends MongoRepository<Customer, String> {

}
