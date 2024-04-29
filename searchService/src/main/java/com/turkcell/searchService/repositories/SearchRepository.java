package com.turkcell.searchService.repositories;

import com.turkcell.searchService.entities.Search;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchRepository extends MongoRepository<Search, String> {

}
