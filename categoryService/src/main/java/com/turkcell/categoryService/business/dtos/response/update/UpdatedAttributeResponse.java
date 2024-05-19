package com.turkcell.categoryService.business.dtos.response.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedAttributeResponse {
    private int id;
    private String attributeName;
}
