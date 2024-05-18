package com.turkcell.categoryService.business.dtos.request.create;

import com.turkcell.categoryService.business.dtos.dto.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductAttributeDetailsRequest {

    private AttributeDTO attributeDTO;
    private String attributeValue;
}
