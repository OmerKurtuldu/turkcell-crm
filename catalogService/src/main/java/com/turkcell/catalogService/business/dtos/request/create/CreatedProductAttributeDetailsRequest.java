package com.turkcell.catalogService.business.dtos.request.create;

import com.turkcell.catalogService.business.dtos.dto.AttributeDTO;
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
