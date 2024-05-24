package com.turkcell.catalogService.business.dtos.response.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedFeatureResponse {
    private int id;
    private String name;
}
