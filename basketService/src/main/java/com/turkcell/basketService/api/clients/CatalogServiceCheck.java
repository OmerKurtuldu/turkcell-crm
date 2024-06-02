package com.turkcell.basketService.api.clients;


import com.turkcell.basketService.business.dtos.response.GetProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("basketservice/api/v1/basket")
public class CatalogServiceCheck {

    private final CatalogServiceClient catalogServiceClient;

    @GetMapping("/products/{id}")
    public GetProductResponse product(@PathVariable int id) {
        return catalogServiceClient.productGetById(id);
    }
}
