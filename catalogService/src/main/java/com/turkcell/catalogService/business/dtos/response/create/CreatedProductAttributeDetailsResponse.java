package com.turkcell.catalogService.business.dtos.response.create;

import com.turkcell.catalogService.business.dtos.dto.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductAttributeDetailsResponse {
    private int id;
    private AttributeDTO attributeDTO;
    private String attributeValue;
}
