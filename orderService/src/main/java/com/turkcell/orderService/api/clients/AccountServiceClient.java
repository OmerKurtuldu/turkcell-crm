package com.turkcell.orderService.api.clients;


import com.turkcell.orderService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.orderService.configurations.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "http://localhost:9002",configuration = FeignConfig.class)
public interface AccountServiceClient {

    @GetMapping("/accountservice/api/v1/account/{id}")
    GetAccountResponse accountGetById(@PathVariable int id);

}

