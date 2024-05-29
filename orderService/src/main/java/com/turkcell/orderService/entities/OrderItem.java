package com.turkcell.orderService.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderItem {

    private int productId;
    private String productName;
    private Double price;

}
