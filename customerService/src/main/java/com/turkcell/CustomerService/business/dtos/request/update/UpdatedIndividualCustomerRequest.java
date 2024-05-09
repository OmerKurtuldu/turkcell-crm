package com.turkcell.CustomerService.business.dtos.request.update;

import com.turkcell.commonpackage.utils.enums.GenderType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedIndividualCustomerRequest {

    @NotNull
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private GenderType genderType;
    private String fatherName;
    private String motherName;
    private String nationalityNo;
    private String homePhone;
    private UpdatedCustomerRequest updatedCustomerRequest;

}
