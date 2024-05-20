package com.turkcell.customerService.business.dtos.request.create;

import com.turkcell.commonpackage.utils.enums.GenderType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedIndividualCustomerRequest {
    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private GenderType genderType;

    private String fatherName;

    private String motherName;

    @NotNull
    @Size(min = 11, max = 11)
    private String nationalityNo;

    @Size(min = 11, max = 11)
    private String homePhone;

    @Valid
    private CreatedCustomerRequest createCustomerRequest;
}
