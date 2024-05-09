package com.turkcell.CustomerService.business.dtos.response.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAddressResponse {
    private String street;
    private String houseFlatNumber;
    private String description;
    private String firstName;
    private String lastName;
    private String cityName;
}
