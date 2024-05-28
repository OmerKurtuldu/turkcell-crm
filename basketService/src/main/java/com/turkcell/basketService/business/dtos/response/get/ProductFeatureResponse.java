package com.turkcell.basketService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFeatureResponse {
    private String featureName;
    private String value;
}
