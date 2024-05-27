package com.turkcell.basketService.business.concretes;

import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.basketService.business.abstracts.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class BasketManager implements BasketService {

    private RedisRepository redisRepository;

    @Override
    public void add(String customerId, String productId) {

        Basket basket = redisRepository.getBasketByCustomerId(customerId);

        if (basket == null) {
            basket = new Basket();
            basket.setCustomerId(customerId);
        }
        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(productId);
        basketItem.setProductName("Modem");
        basketItem.setPrice(2000);
        basket.setCustomerId(customerId);
        basket.setTotalPrice(basket.getTotalPrice()+basketItem.getPrice());
        basket.getBasketItems().add(basketItem);
        redisRepository.addItem(basket);
    }

    @Override
    public Map<String, Basket> getAllItems() {
        return redisRepository.getAllItems();
    }
}