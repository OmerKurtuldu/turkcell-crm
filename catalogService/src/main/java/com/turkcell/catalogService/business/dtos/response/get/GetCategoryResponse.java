package com.turkcell.catalogService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetCategoryResponse {
    private int id;
    private String categoryName;

}
