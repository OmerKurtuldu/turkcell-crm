package com.turkcell.catalogService.business.dtos.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatedFeatureRequest {
    private int id;
    private String name;
}
