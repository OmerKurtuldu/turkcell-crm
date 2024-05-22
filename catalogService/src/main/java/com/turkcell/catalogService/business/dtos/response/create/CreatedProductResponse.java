package com.turkcell.catalogService.business.dtos.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductResponse {
    private int id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private List<ProductFeatureResponse> productFeatures;
}

