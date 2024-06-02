package com.turkcell.basketService.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Basket implements Serializable {

    private String id;
    private String accountId;
    private Double totalPrice;
    private List<BasketItem> basketItems;

    public Basket() {
        this.basketItems = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public Basket(String accountId) {
        this.basketItems = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
        this.totalPrice = 0.0;
        this.accountId = accountId;
    }
}