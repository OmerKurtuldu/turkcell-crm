package com.turkcell.customerService.business.dtos.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCustomerRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String mobilePhone;
}
