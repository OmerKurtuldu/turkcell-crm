package com.turkcell.catalogService.business.dtos.response.get;

import com.turkcell.catalogService.business.dtos.dto.AttributeDTO;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductAttributeDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetProductAttributeDetailsResponse {
    private int id;
    private AttributeDTO attributeDTO;
    private String attributeName;
    private String attributeValue;

}
