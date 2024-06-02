package com.turkcell.basketService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedBasketRequest {

    private String accountId;
    private int productId;
}
