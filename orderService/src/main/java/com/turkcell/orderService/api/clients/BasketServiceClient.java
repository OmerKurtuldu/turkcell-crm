package com.turkcell.orderService.api.clients;

import com.turkcell.orderService.business.dtos.response.get.GetBasketResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "basket-service", url = "http://localhost:9007")
public interface BasketServiceClient {

    @GetMapping("basketservice/api/v1/basket/{basketId}")
    GetBasketResponse basketGetById(@PathVariable String basketId);

    @DeleteMapping("basketservice/api/v1/basket/{basketId}")
    void basketDeleteById(@PathVariable String basketId);
}
