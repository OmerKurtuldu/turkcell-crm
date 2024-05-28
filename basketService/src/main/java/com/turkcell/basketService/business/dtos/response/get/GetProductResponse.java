package com.turkcell.basketService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetProductResponse {
    //todo : int -> Integer
    private int id;
    private String name;
    private int quantity;
    private Integer categoryId;
    private BigDecimal price;
    private List<ProductFeatureResponse> productFeatures;
}
