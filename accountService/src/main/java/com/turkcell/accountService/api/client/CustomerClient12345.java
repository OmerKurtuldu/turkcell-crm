package com.turkcell.accountService.api.client;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/accountservice/api/v1/customers")
public class CustomerClient12345 {

    private final CustomerClient customerClient;

    @GetMapping("/CustomerGetById/{id}")
    public ClientResponse örnekİstemciMetodu(@PathVariable int id) {
        return customerClient.customerGetById(id);
    }

}
