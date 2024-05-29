package com.turkcell.basketService.business.concretes;

import com.turkcell.basketService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.basketService.business.dtos.response.get.GetProductResponse;
import com.turkcell.basketService.business.rules.BasketBusinessRules;
import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.basketService.business.abstracts.BasketService;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {

    private final RedisRepository redisRepository;
    private final BasketBusinessRules basketBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public void add(String accountId, int productId) {

        basketBusinessRules.checkAccountAvailabilityForBasket(accountId);
        GetProductResponse getProductResponse = basketBusinessRules.checkProductAvailabilityForBasket(productId);

        Basket basket = redisRepository.getBasketByAccountId(accountId);

        if (basket == null) {
            basket = new Basket();
            basket.setAccountId(accountId);
            basket.setTotalPrice(0.0);
        }

        BasketItem basketItem = this.modelMapperService.forResponse().map(getProductResponse, BasketItem.class);
        basketItem.setId(UUID.randomUUID().toString());
        basket.setAccountId(accountId);
        basket.setTotalPrice(basket.getTotalPrice()+basketItem.getPrice());
        basket.getBasketItems().add(basketItem);
        redisRepository.addItem(basket);
    }

    @Override
    public Map<String, Basket> getAllItems() {
        return redisRepository.getAllItems();
    }

    @Override
    public GetBasketResponse getByItems(String basketId) {

        basketBusinessRules.checkExistBasketByBasketId(basketId);
        Basket basket = redisRepository.getByBasket(basketId);
        return this.modelMapperService.forResponse().map(basket,GetBasketResponse.class);
    }

    @Override
    public void deleteBasketItem(String basketId, String basketItemId) {

        basketBusinessRules.checkExistBasketItemsByBasketItemId(basketId,basketItemId);

        redisRepository.deleteBasketItem(basketId,basketItemId);
    }

    @Override
    public void deleteItem(String basketId) {

        basketBusinessRules.checkExistBasketByBasketId(basketId);

        redisRepository.deleteItem(basketId);
    }

}