package com.turkcell.basketService.business.concretes;

import com.turkcell.basketService.business.dtos.request.CreatedBasketRequest;
import com.turkcell.basketService.business.dtos.response.CreatedBasketResponse;
import com.turkcell.basketService.business.dtos.response.GetBasketResponse;
import com.turkcell.basketService.business.dtos.response.GetProductResponse;
import com.turkcell.basketService.business.rules.BasketBusinessRules;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.basketService.entites.Basket;
import com.turkcell.basketService.entites.BasketItem;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketManagerTest {

    @Mock
    private RedisRepository redisRepository;
    @Mock
    private BasketBusinessRules basketBusinessRules;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private BasketManager basketManager;

    @BeforeEach
    void setUp() {
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }

    private Basket createBasket(String accountId, double totalPrice) {
        Basket basket = new Basket();
        basket.setAccountId(accountId);
        basket.setTotalPrice(totalPrice);
        basket.setBasketItems(new ArrayList<>());
        return basket;
    }

    private GetProductResponse createProductResponse(int productId) {
        return new GetProductResponse(productId, "Product1", 1, 1, new BigDecimal("10.00"), new ArrayList<>());
    }


    @Test
    void getByItemsWhenSuccess() {
        // Given
        String basketId = UUID.randomUUID().toString();
        Basket basket = createBasket("account123", 20.0);
        GetBasketResponse getBasketResponse = new GetBasketResponse(basketId, "account123", 20.0, new ArrayList<>());

        when(redisRepository.getByBasketId(basketId)).thenReturn(basket);
        when(modelMapper.map(any(Basket.class), eq(GetBasketResponse.class))).thenReturn(getBasketResponse);

        // When
        GetBasketResponse response = basketManager.getByItems(basketId);

        // Then
        Assertions.assertEquals(getBasketResponse.getId(), response.getId());
        Assertions.assertEquals(getBasketResponse.getAccountId(), response.getAccountId());
    }

}