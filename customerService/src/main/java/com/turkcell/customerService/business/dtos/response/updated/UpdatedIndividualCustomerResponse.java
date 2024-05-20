package com.turkcell.customerService.business.dtos.response.updated;

import com.turkcell.commonpackage.utils.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedIndividualCustomerResponse {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private GenderType gender;
    private String fatherName;
    private String motherName;
    private String email;
    private String homePhone;
    private String mobilePhone;

}

