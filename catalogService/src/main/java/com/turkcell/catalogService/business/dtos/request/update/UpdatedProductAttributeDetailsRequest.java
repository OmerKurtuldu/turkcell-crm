package com.turkcell.catalogService.business.dtos.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedProductAttributeDetailsRequest {
    private int id;
    private int attributeId;
    private String attributeValue;
}
