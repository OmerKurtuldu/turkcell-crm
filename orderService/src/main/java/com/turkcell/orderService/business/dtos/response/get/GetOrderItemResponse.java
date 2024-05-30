package com.turkcell.orderService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetOrderItemResponse {
    private int productId;
    private String productName;
    private Double price;
}
