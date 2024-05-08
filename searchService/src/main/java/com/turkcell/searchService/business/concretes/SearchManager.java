package com.turkcell.searchService.business.concretes;

import com.turkcell.searchService.business.abstracts.SearchService;
import com.turkcell.searchService.entities.concretes.Customer;
import com.turkcell.searchService.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {


    private MongoOperations mongoOperations;


    @Override
    public List<Customer> customerList(Customer search) {
        Query query = new Query();

        if(search.getFirstName() != null){
            query.addCriteria(Criteria.where("firstName").regex(search.getFirstName(),"i"));
        }

        if(search.getSecondName() != null){
            query.addCriteria(Criteria.where("secondName").regex(search.getSecondName(),"i"));
        }

        if(search.getFatherName() != null){
            query.addCriteria(Criteria.where("fatherName").regex(search.getFatherName(),"i"));
        }

        if(search.getMotherName() != null){
            query.addCriteria(Criteria.where("motherName").regex(search.getMotherName(),"i"));
        }

        if(search.getLastName() != null){
            query.addCriteria(Criteria.where("lastName").regex(search.getLastName(),"i"));
        }

        if(search.getNationalityNumber() != null){
            query.addCriteria(Criteria.where("nationalityNumber").regex(search.getNationalityNumber(),"i"));
        }

        if(search.getId() != null){
            query.addCriteria(Criteria.where("id").regex(search.getId(),"i"));
        }



        return mongoOperations.find(query, Customer.class);
    }
}
