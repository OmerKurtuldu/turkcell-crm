package com.turkcell.accountService.api.client;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:9001")
public interface CustomerServiceClient {

    @GetMapping("/individualcustomerservice/api/v1/customers/customerClient/{id}")
    ClientResponse customerGetById(@PathVariable int id);

    @GetMapping("/individualcustomerservice/api/v1/customers/addressClient/{id}")
    ClientResponse addressGetById(@PathVariable int id);
}
