package com.turkcell.CustomerService.business.dtos.response.create;

import com.turkcell.CustomerService.entities.concretes.City;
import com.turkcell.CustomerService.entities.concretes.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedAddressResponse {
    private String street;
    private String houseFlatNumber;
    private String description;
    private String firstName;
    private String lastName;
    private String cityName;
}
