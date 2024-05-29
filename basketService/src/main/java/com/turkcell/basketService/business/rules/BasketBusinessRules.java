package com.turkcell.basketService.business.rules;

import com.turkcell.basketService.api.clients.AccountServiceClient;
import com.turkcell.basketService.api.clients.CatalogServiceClient;
import com.turkcell.basketService.business.dtos.response.get.GetProductResponse;
import com.turkcell.basketService.business.messages.Messages;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.basketService.entites.Basket;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketBusinessRules {
    private final MessageService messageService;
    private final AccountServiceClient accountServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final RedisRepository redisRepository;

    public void checkAccountAvailabilityForBasket(String accountId){
        ClientResponse response = accountServiceClient.accountGetById(Integer.parseInt(accountId));
        if(!response.isSuccess()){
            throw new BusinessException(messageService.getMessage(Messages.BasketErrors.AccountRegistrationShouldBeExist));
        }
    }

    public GetProductResponse checkProductAvailabilityForBasket(int productId){
        try {
            GetProductResponse getProductResponse = catalogServiceClient.productGetById(productId);
            return getProductResponse;
        }
        catch(FeignException e){
            //todo burası yorumlanacak
            throw new BusinessException(messageService.getMessage(Messages.BasketErrors.ProductRegistrationShouldBeExist));
        }
    }

    public void checkExistBasketByBasketId(String basketId){
        Basket basket = redisRepository.getByBasket(basketId);
        if (basket == null) {
            throw new BusinessException(messageService.getMessage(Messages.BasketErrors.BasketShouldBeExist));
        }
    }

    public void checkExistBasketItemsByBasketItemId(String basketId,String basketItemId){
        checkExistBasketByBasketId(basketId);
        Basket basket = redisRepository.getByBasket(basketId);
        boolean basketItemExists = basket.getBasketItems().stream()
                .anyMatch(item -> item.getId().equals(basketItemId));
        if (!basketItemExists) {
            throw new BusinessException(messageService.getMessage(Messages.BasketErrors.BasketItemShouldBeExist));
        }

    }



}
