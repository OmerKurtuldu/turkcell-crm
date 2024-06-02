package com.turkcell.basketService.dataAccess;

import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
Redis (Remote Dictionary Server), açık kaynaklı,bellek içi (in-memory)
veri yapısı deposu olarak kullanılan bir veritabanı yönetim sistemidir.
Redis, verileri bellek (RAM) içinde tutarak çok hızlı okuma ve yazma işlemleri gerçekleştirebilir.
Redis, in-memory çalışan bir veritabanıdır ve key-value şeklinde veri tutabilir.
*/
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

    public void addBasket(Basket basket){
        this.hashOperations.put(Key,basket.getId()+"_"+basket.getAccountId(),basket);
    }

    public Basket getBasketByAccountId(String accountId) {
        return hashOperations.entries(Key).values().stream()
                .filter(basket -> accountId.equals(basket.getAccountId()))
                .findFirst()
                .orElse(null);
    }

    public Basket getByBasketId(String basketId) {
        return this.hashOperations.get(Key, basketId);
    }

    public void deleteBasket(String basketId){
        this.hashOperations.delete(Key,basketId);
    }

    public void deleteBasketItem(String basketId, String basketItemId) {
        // Basket'i Redis'ten al
        Basket basket = this.hashOperations.get(Key, basketId);

        if (basket != null) {
            List<BasketItem> items = basket.getBasketItems();
            // Iterator, koleksiyonlar üzerinde gezinmek ve ogeleri güvenli bir sekilde silmek icin kullanılır.
            Iterator<BasketItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                BasketItem item = iterator.next();
                if (item.getId().equals(basketItemId)) {
                    iterator.remove();
                    break;
                }
            }

            // Güncellenmis basket'i tekrar Redis'e kaydet
            this.hashOperations.put(Key, basketId, basket);
        }
    }
}