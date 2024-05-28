package com.turkcell.basketService.business.abstracts;

import com.turkcell.basketService.entites.Basket;
import java.util.Map;

public interface BasketService {
    void add(String accountId,int productId);
    Map<String, Basket> getAllItems();

}