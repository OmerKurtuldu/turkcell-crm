package com.turkcell.basketService.business.abstracts;

import com.turkcell.basketService.business.dtos.request.create.CreatedBasketRequest;
import com.turkcell.basketService.business.dtos.response.create.CreatedBasketResponse;
import com.turkcell.basketService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.basketService.entites.Basket;
import java.util.Map;

public interface BasketService {
    CreatedBasketResponse add(CreatedBasketRequest createdBasketRequest);
    Map<String, Basket> getAllItems();
    GetBasketResponse getByItems(String basketId);
    void deleteBasketItem(String basketId, String basketItemId);
    void deleteItem(String basketId);

}