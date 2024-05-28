package com.turkcell.basketService.api.controllers;

import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.business.abstracts.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("basketservice/api/v1/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    public void addItem(@RequestParam String accountId,@RequestParam int productId){
        basketService.add(accountId,productId);
    }

    @GetMapping
    public Map<String, Basket> getAllItems(){
        return basketService.getAllItems();
    }
}