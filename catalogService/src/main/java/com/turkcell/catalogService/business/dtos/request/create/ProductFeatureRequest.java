package com.turkcell.catalogService.business.dtos.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFeatureRequest {
    private int featureId;
    private String value;

}
