package com.turkcell.categoryService.business.dtos.response.update;

import com.turkcell.categoryService.business.dtos.response.create.CreatedAttributeResponse;
import com.turkcell.categoryService.entities.concretes.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedProductResponse {

    private int id;
    private String productName;
    private int quantity;
    private Double price;
    private int categoryId;
    private List<CreatedAttributeResponse> attributes;
}
