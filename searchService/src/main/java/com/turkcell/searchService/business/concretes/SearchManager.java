package com.turkcell.searchService.business.concretes;

import com.turkcell.searchService.business.abstracts.SearchService;
import com.turkcell.searchService.entities.concretes.Customer;
import com.turkcell.searchService.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {

    private MongoOperations mongoOperations;

    @Override
    public List<Customer> customerList(Customer search) {
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl
//                = "http://localhost:9008/individualcustomerservice/api/v1/customers/4";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl ,
//                String.class);
//        String body = response.getBody();
//        System.out.println(body);
        Query query = new Query();
        Map<String,String> dtoMap = new HashMap<>();
        dtoMap.put("id" ,search.getId());
        dtoMap.put("firstName" ,search.getFirstName());
        dtoMap.put("lastName" ,search.getLastName());
        dtoMap.put("nationalityNumber" ,search.getNationalityNumber());
        dtoMap.put("mobilePhone" ,search.getMobilePhone());
        for (Map.Entry<String, String> entry : dtoMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(value != null){
                System.out.println(key + "  "+ value);

                query.addCriteria(Criteria.where(key).regex(value,"i"));
            }
        }

//        Query query = new Query();
//
//        if(search.getFirstName() != null){
//            query.addCriteria(Criteria.where("firstName").regex(search.getFirstName(),"i"));
//        }
//
//        if(search.getLastName() != null){
//            query.addCriteria(Criteria.where("lastName").regex(search.getLastName(),"i"));
//        }
//
//        if(search.getNationalityNumber() != null){
//            query.addCriteria(Criteria.where("nationalityNumber").regex(search.getNationalityNumber(),"i"));
//        }
//
//        if(search.getId() != null){
//            query.addCriteria(Criteria.where("id").regex(search.getId(),"i"));
//        }



        return mongoOperations.find(query, Customer.class);
    }
}
