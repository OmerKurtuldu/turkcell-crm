package com.turkcell.basketService.business.concretes;

import com.turkcell.basketService.api.client.AccountServiceClient;
import com.turkcell.basketService.api.client.CatalogServiceClient;
import com.turkcell.basketService.business.dtos.response.get.GetProductResponse;
import com.turkcell.basketService.business.rules.BasketBusinessRules;
import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.basketService.business.abstracts.BasketService;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketManager implements BasketService {

    private final RedisRepository redisRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final BasketBusinessRules basketBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public void add(String accountId, int productId) {

        basketBusinessRules.checkAccountAvailabilityForBasket(accountId);
        GetProductResponse getProductResponse = catalogServiceClient.productGetById(productId);

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
}