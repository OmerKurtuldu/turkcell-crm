package com.turkcell.CustomerService.business.dtos.response.get;

import com.turkcell.CustomerService.entities.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetIndividualCustomerResponse {
    private int id;
    private LocalDateTime createdDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private GenderType gender;
    private String fatherName;
    private String motherName;
    private String nationalityNo;
    private String mobilePhone;
    private String email;
    private String homePhone;
}
