package com.turkcell.customerService.business.dtos.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedAddressRequest {
    @NotNull
    private int id;

    private int cityId;


    private String street;


    private String houseFlatNumber;


    private String description;


    private int customerId;

}
