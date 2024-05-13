package com.turkcell.accountService.api.controllers;

import com.turkcell.accountService.consumer.CustomerConsumer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/accountservice/api/v1/customers")
public class FeignController {

    private final CustomerConsumer customerConsumer;

    @GetMapping("/CustomerGetById/{id}")
    public Object örnekİstemciMetodu(@PathVariable int id) {
        return customerConsumer.customerGetById(id);
    }

}
