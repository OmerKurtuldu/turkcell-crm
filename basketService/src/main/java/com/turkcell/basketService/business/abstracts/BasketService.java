package com.turkcell.basketService.business.abstracts;

import com.turkcell.basketService.business.dtos.request.CreatedBasketRequest;
import com.turkcell.basketService.business.dtos.response.CreatedBasketResponse;
import com.turkcell.basketService.business.dtos.response.GetBasketResponse;
import com.turkcell.basketService.entites.Basket;
import java.util.Map;

public interface BasketService {
    CreatedBasketResponse add(CreatedBasketRequest createdBasketRequest);
    Map<String, Basket> getAllItems();
    GetBasketResponse getByItems(String basketId);
    void deleteBasketItem(String basketId, String basketItemId);
    void deleteItem(String basketId);

}