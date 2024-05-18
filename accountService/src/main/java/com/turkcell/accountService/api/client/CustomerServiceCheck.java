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
public class CustomerServiceCheck {

    private final CustomerServiceClient customerServiceClient;

    @GetMapping("/CustomerCheck/{id}")
    public ClientResponse CustomerCheck(@PathVariable int id) {
        return customerServiceClient.customerGetById(id);
    }

    @GetMapping("/AddressCheck/{id}")
    public ClientResponse AddressCheck(@PathVariable int id) {
        return customerServiceClient.addressGetById(id);
    }

    @GetMapping("/customer/{id}")
    public Object customer(@PathVariable int id) {
        return customerServiceClient.customer(id);
    }
}
