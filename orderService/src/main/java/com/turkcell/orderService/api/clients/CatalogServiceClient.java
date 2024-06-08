package com.turkcell.orderService.api.clients;

import com.turkcell.orderService.business.dtos.response.get.GetProductResponse;
import com.turkcell.orderService.configurations.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "http://localhost:9005",configuration = FeignConfig.class)
public interface CatalogServiceClient {

    @GetMapping("/catalogservice/api/v1/products/{id}")
    GetProductResponse productGetById(@PathVariable int id);

}
