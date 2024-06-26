package com.turkcell.basketService.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem implements Serializable {

    private String id;
    private int productId;
    private String productName;
    private Double price;

}