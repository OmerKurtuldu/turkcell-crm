package com.turkcell.catalogService.business.dtos.response.getall;

import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllProductResponse {
    private int id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private List<ProductFeatureResponse> productFeatures;
}
