package com.turkcell.categoryService.business.dtos.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductAttributeDetailsRequest {

    private int attributeId;
    private String attributeValue;
}
