package com.turkcell.searchService.api.controllers;

import com.turkcell.searchService.business.abstracts.SearchService;
import com.turkcell.searchService.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/searchservice/api/v1/search")
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/customers")
    public List<Customer> searchCustomers(@RequestBody Customer search){
        return searchService.customerList(search);
    }

}
