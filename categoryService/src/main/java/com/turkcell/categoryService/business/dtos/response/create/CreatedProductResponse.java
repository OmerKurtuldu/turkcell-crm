package com.turkcell.categoryService.business.dtos.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.events.Attribute;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedProductResponse {

    private int id;
    private String productName;
    private int quantity;
    private Double price;
    private int categoryId;
    private List<CreatedAttributeResponse> attributes;

}
