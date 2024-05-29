package com.turkcell.basketService.business.dtos.response.get;

import com.turkcell.basketService.entites.BasketItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetBasketResponse {
    private String id;
    private String accountId;
    private Double totalPrice;
    private List<BasketItem> basketItems;
}
