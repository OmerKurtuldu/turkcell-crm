package com.turkcell.catalogService.business.dtos.response.update;

import com.turkcell.catalogService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.catalogService.business.dtos.response.create.CreatedProductAttributeDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedProductResponse {

    private int id;
    private String productName;
    private int quantity;
    private Double price;
    private int categoryId;
    private List<CreatedProductAttributeDetailsResponse> attributes;

}
