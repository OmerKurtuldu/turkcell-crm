package com.turkcell.basketService.api.controllers;

import com.turkcell.basketService.business.dtos.request.CreatedBasketRequest;
import com.turkcell.basketService.business.dtos.response.CreatedBasketResponse;
import com.turkcell.basketService.business.dtos.response.GetBasketResponse;
import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.business.abstracts.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("basketservice/api/v1/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBasketResponse addItem(@RequestBody CreatedBasketRequest createdBasketRequest){
        return basketService.add(createdBasketRequest);
    }

    @GetMapping
    public Map<String, Basket> getAllItems(){
        return basketService.getAllItems();
    }

    @GetMapping("/{basketId}")
    public GetBasketResponse getById(@PathVariable String basketId){
        return basketService.getByItems(basketId);
    }

    @DeleteMapping("/{basketId}/items/{basketItemId}")
    public ResponseEntity<Void> deleteBasketItem(@PathVariable String basketId, @PathVariable String basketItemId) {
        basketService.deleteBasketItem(basketId, basketItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{basketId}")
    public ResponseEntity<Void> deleteItem(@PathVariable String basketId) {
        basketService.deleteItem(basketId);
        return ResponseEntity.noContent().build();
    }
}