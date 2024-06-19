package com.turkcell.basketService.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketItemResponse {
    private String id;
    private int productId;
    private String productName;
    private Double price;
}
