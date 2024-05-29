package com.turkcell.orderService.api.clients;

import com.turkcell.orderService.business.dtos.response.get.GetBasketResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basket-service", url = "http://localhost:8084")
public interface BasketServiceClient {

    @GetMapping("basketservice/api/v1/basket/{basketId}")
    GetBasketResponse basketGetById(@PathVariable String basketId);
}
