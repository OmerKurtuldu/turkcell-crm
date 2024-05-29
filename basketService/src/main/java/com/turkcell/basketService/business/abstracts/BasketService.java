package com.turkcell.basketService.business.abstracts;

import com.turkcell.basketService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.basketService.entites.Basket;
import java.util.Map;

public interface BasketService {
    void add(String accountId,int productId);
    Map<String, Basket> getAllItems();
    GetBasketResponse getByItems(String basketId);
    void deleteBasketItem(String basketId, String basketItemId);
    void deleteItem(String basketId);

}