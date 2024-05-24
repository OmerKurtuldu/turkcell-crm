package com.turkcell.catalogService.business.dtos.request.update;

import com.turkcell.catalogService.business.dtos.dto.CategoryDTO;
import com.turkcell.catalogService.business.dtos.request.create.ProductFeatureRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedProductRequest {
    private int id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private CategoryDTO categoryDTO;
    private List<ProductFeatureRequest> productFeatures;
}
