package com.turkcell.catalogService.business.dtos.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedProductRequest {
    private int id;
    private String productName;
    private int quantity;
    private Double price;
    private int categoryId;
    private List<Integer> attributeId;
}
