package com.turkcell.searchService.business.concretes;

import com.turkcell.searchService.business.abstracts.SearchService;
import com.turkcell.searchService.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {

    private MongoOperations mongoOperations;

    @Override
    public List<Customer> searchCustomer(Customer customerSearch) {
        Query query = new Query();
        Map<String, String> dtoMap = new HashMap<>();
        dtoMap.put("id", customerSearch.getId());
        dtoMap.put("firstName", customerSearch.getFirstName());
        dtoMap.put("lastName", customerSearch.getLastName());
        dtoMap.put("nationalityNumber", customerSearch.getNationalityNumber());
        dtoMap.put("mobilePhone", customerSearch.getMobilePhone());

        for (Map.Entry<String, String> entry : dtoMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                System.out.println(key + "  " + value);
                query.addCriteria(Criteria.where(key).regex(value, "i"));
            }
        }
        return mongoOperations.find(query, Customer.class);
    }
}
