package com.turkcell.basketService.dataAccess;

import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Repository
public class RedisRepository {

    public static final String Key="BASKET";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String,String,Basket> hashOperations;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations=redisTemplate.opsForHash();
    }

    public Map<String,Basket> getAllItems(){
        return this.hashOperations.entries(Key);
    }

    public void addItem(Basket basket){
        this.hashOperations.put(Key,basket.getId()+"_"+basket.getAccountId(),basket);
    }

    public Basket getBasketByAccountId(String accountId) {
        return hashOperations.entries(Key).values().stream()
                .filter(basket -> accountId.equals(basket.getAccountId()))
                .findFirst()
                .orElse(null);
    }

    public Basket getByBasket(String basketId) {
        return this.hashOperations.get(Key, basketId);
    }

    public void deleteItem(String basketId){
        this.hashOperations.delete(Key,basketId);
    }

    public void deleteBasketItem(String basketId, String basketItemId) {
        // Basket'ı Redis'ten al
        Basket basket = this.hashOperations.get(Key, basketId);

        if (basket != null) {
            List<BasketItem> items = basket.getBasketItems();
            // BasketItem listesinden ilgili öğeyi sil
            Iterator<BasketItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                BasketItem item = iterator.next();
                if (item.getId().equals(basketItemId)) {
                    iterator.remove();
                    break;
                }
            }

            // Güncellenmiş basket'ı tekrar Redis'e kaydet
            this.hashOperations.put(Key, basketId, basket);
        }
    }
}