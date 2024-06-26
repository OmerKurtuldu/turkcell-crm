package com.turkcell.searchService.repositories;

import com.turkcell.searchService.entities.concretes.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    // TODO: 19.06.2024 herhangi uyuşan bir kayıtta getirmesi gerekiyor 
    @Query("{$or:[{ 'firstName' : { $regex: ?0, $options: 'i' }}, { 'secondName' : { $regex: ?0, $options: 'i' }}, { 'lastName' : { $regex: ?0, $options: 'i' }}, { 'birthDate' : { $regex: ?0, $options: 'i' }}, { 'gender' : { $regex: ?0, $options: 'i' }}, { 'fatherName' : { $regex: ?0, $options: 'i' }}, { 'motherName' : { $regex: ?0, $options: 'i' }}, { 'nationalityNumber' : { $regex: ?0, $options: 'i' }}]}")
    List<Customer> findAllBySearchText(String searchText);
}
