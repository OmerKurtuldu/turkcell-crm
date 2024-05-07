package com.turkcell.CustomerService.business.dtos.request.create;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCityRequest {
    private String cityName;
}
