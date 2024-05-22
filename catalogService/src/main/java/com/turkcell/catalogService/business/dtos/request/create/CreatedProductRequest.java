package com.turkcell.catalogService.business.dtos.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductRequest {
    private String productName;
    private int quantity;
    private Double price;
    private int categoryId;
    private List<Integer> AttributeDetailsId;
}
