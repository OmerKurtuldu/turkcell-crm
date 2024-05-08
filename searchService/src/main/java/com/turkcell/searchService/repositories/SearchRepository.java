package com.turkcell.searchService.repositories;

import com.turkcell.searchService.entities.concretes.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SearchRepository extends MongoRepository<Customer, String> {

    @Query("{$or:[{ 'firstName' : { $regex: ?0, $options: 'i' }}, { 'secondName' : { $regex: ?0, $options: 'i' }}, { 'lastName' : { $regex: ?0, $options: 'i' }}, { 'birthDate' : { $regex: ?0, $options: 'i' }}, { 'gender' : { $regex: ?0, $options: 'i' }}, { 'fatherName' : { $regex: ?0, $options: 'i' }}, { 'motherName' : { $regex: ?0, $options: 'i' }}, { 'nationalityNumber' : { $regex: ?0, $options: 'i' }}]}")
    List<Customer> findAllBySearchText(String searchText);
}
