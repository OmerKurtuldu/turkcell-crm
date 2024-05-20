package com.turkcell.customerService.business.dtos.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedAddressRequest {

    @NotNull
    private int cityId;

    @NotNull
    private String street;

    @NotNull
    private String houseFlatNumber;

    @NotNull
    private String description;

    @NotNull
    private int customerId;



}
