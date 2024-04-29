package com.turkcell.CustomerService.business.dtos.request.update;

import com.turkcell.CustomerService.entities.enums.GenderType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCustomerRequest {
   private String email;
   private String mobilePhone;
}
