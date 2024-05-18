package com.turkcell.categoryService.business.dtos.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductAttributeDetailsResponse {
    private int id;
    private int attributeId;
    private String attributeValue;
}
