package com.turkcell.categoryService.business.dtos.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedCategoryRequest {

    private int id;
    private String name;
}
