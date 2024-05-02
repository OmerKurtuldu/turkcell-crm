package com.turkcell.CustomerService.business.dtos.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCustomerRequest {
   private String email;
   private String mobilePhone;
}
