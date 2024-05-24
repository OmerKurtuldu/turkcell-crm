package com.turkcell.catalogService.business.dtos.response.getall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllCategoryResponse {
    private int id;
    private String categoryName;
}
