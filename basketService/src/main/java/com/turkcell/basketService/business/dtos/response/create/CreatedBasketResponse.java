package com.turkcell.basketService.business.dtos.response.create;

import com.turkcell.basketService.business.dtos.BasketItemResponse;
import com.turkcell.basketService.entites.BasketItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedBasketResponse {
    private String basketId;
    private String accountId;
    private Double totalPrice;
    private List<BasketItemResponse> basketItems;
}
