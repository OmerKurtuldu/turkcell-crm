package com.turkcell.orderService.api.clients;

import com.turkcell.orderService.business.dtos.response.get.GetAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:9001")

public interface CustomerServiceClient {

    @GetMapping("/customerservice/api/v1/address/{id}")
    GetAddressResponse addressGetById(@PathVariable int id);

}
