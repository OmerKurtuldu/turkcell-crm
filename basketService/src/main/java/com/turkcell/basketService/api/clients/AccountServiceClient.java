package com.turkcell.basketService.api.clients;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "http://localhost:9002")
public interface AccountServiceClient {

    @GetMapping("/accountservice/api/v1/account/accountClient/{id}")
    ClientResponse accountGetById(@PathVariable int id);

}
