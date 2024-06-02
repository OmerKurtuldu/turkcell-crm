package com.turkcell.catalogService.business.dtos.response.get;

import com.turkcell.catalogService.business.dtos.response.create.ProductFeatureResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetProductResponse {

    private int id;
    private String name;
    private int quantity;
    private Integer categoryId;
    private BigDecimal price;
    private List<ProductFeatureResponse> productFeatures;
}
