package com.turkcell.basketService.business.concretes;

import com.turkcell.basketService.business.rules.BasketBusinessRules;
import com.turkcell.basketService.dataAccess.RedisRepository;
import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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

    private void modelMapperForResponse() {
        when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }

//    @Test
//    void addWhenSuccess() {
//        // Given
//        modelMapperForResponse();
//
//        String accountId = "account123";
//        int productId = 1;
//        GetProductResponse getProductResponse = new GetProductResponse(productId, "Product1", 1, 1, new BigDecimal("10.00"), new ArrayList<>());
//        BasketItem basketItem = new BasketItem(UUID.randomUUID().toString(), productId, "Product1", 10.0);
//
//        Basket basket = new Basket();
//        basket.setAccountId(accountId);
//        basket.setTotalPrice(0.0);
//
//        when(redisRepository.getBasketByAccountId(accountId)).thenReturn(null);
//        when(modelMapper.map(any(GetProductResponse.class), eq(BasketItem.class))).thenReturn(basketItem);
//        when(basketBusinessRules.checkProductAvailabilityForBasket(productId)).thenReturn(getProductResponse);
//
//        // When
//        basketManager.add(accountId, productId);
//
//        // Then
//        verify(redisRepository, times(1)).addBasket(any(Basket.class));
//    }
//
//    @Test
//    void getAllItemsWhenSuccess() {
//        // Given
//        Map<String, Basket> baskets = new HashMap<>();
//        baskets.put("basket1", new Basket(UUID.randomUUID().toString(), "account123", 20.0, new ArrayList<>()));
//
//        when(redisRepository.getAllItems()).thenReturn(baskets);
//
//        // When
//        Map<String, Basket> response = basketManager.getAllItems();
//
//        // Then
//        Assertions.assertEquals(baskets.size(), response.size());
//        Assertions.assertEquals(baskets.get("basket1").getTotalPrice(), response.get("basket1").getTotalPrice());
//    }
//
//    @Test
//    void getByItemsWhenSuccess() {
//        // Given
//        modelMapperForResponse();
//
//        String basketId = UUID.randomUUID().toString();
//        Basket basket = new Basket(basketId, "account123", 20.0, new ArrayList<>());
//        GetBasketResponse getBasketResponse = new GetBasketResponse(basketId, "account123", 20.0, new ArrayList<>());
//
//        when(redisRepository.getByBasketId(basketId)).thenReturn(basket);
//        when(modelMapper.map(any(Basket.class), eq(GetBasketResponse.class))).thenReturn(getBasketResponse);
//
//        // When
//        GetBasketResponse response = basketManager.getByItems(basketId);
//
//        // Then
//        Assertions.assertEquals(getBasketResponse.getId(), response.getId());
//        Assertions.assertEquals(getBasketResponse.getAccountId(), response.getAccountId());
//    }
//
//    @Test
//    void deleteBasketItemWhenSuccess() {
//        // Given
//        String basketId = UUID.randomUUID().toString();
//        String basketItemId = UUID.randomUUID().toString();
//
//        doNothing().when(basketBusinessRules).checkExistBasketItemsByBasketItemId(basketId, basketItemId);
//
//        // When
//        basketManager.deleteBasketItem(basketId, basketItemId);
//
//        // Then
//        verify(redisRepository, times(1)).deleteBasketItem(basketId, basketItemId);
//    }
//
//    @Test
//    void deleteItemWhenSuccess() {
//        // Given
//        String basketId = UUID.randomUUID().toString();
//
//        doNothing().when(basketBusinessRules).checkExistBasketByBasketId(basketId);
//
//        // When
//        basketManager.deleteItem(basketId);
//
//        // Then
//        verify(redisRepository, times(1)).deleteBasket(basketId);
//    }
}
