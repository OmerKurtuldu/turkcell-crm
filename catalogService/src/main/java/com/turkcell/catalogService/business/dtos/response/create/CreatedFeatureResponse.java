package com.turkcell.catalogService.business.dtos.response.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedFeatureResponse {
    private int id;
    private String name;
}
