package com.turkcell.accountService.api.client;

import com.turkcell.commonpackage.events.customer.CreatedCustomerEvent;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.util.retry.Retry;

@FeignClient(name = "customer-service", url = "http://localhost:9001")
public interface CustomerClient {

    @GetMapping("/individualcustomerservice/api/v1/customers/customerClient/{id}")
    ClientResponse customerGetById(@PathVariable int id);
}
