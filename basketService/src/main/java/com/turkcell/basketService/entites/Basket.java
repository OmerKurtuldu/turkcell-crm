package com.turkcell.basketService.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Basket implements Serializable {

    private String id;
    private String customerId;
    private double totalPrice;
    private List<BasketItem> basketItems;

    public Basket(){
        this.basketItems = new ArrayList<>();
    }
}