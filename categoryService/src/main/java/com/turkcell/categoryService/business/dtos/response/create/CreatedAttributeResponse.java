package com.turkcell.categoryService.business.dtos.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAttributeResponse {
    private int id;
    private String attributeName;
}
