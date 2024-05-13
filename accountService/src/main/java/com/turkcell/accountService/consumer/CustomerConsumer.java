package com.turkcell.accountService.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service", url = "http://localhost:9001")
public interface CustomerConsumer {

    @GetMapping("/individualcustomerservice/api/v1/customers/{id}")
    public Object customerGetById(@PathVariable int id);
}
