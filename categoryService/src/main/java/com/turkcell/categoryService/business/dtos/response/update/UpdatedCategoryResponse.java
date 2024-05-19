package com.turkcell.categoryService.business.dtos.response.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedCategoryResponse {

    private int id;
    private String name;
}
