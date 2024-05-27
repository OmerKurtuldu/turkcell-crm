package com.turkcell.basketService.business.abstracts;

import com.turkcell.basketService.entites.Basket;
import java.util.Map;

public interface BasketService {
    void add(String customerId,String productId);
    Map<String, Basket> getAllItems();
}