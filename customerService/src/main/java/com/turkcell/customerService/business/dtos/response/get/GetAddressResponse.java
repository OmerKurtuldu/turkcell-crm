package com.turkcell.customerService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAddressResponse {
    private String street;
    private String houseFlatNumber;
    private String description;
    private String firstName;
    private String lastName;
    private String cityName;
}
