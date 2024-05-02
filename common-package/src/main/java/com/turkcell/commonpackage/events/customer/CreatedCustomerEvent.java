package com.turkcell.commonpackage.events.customer;


import com.turkcell.commonpackage.events.Event;
import com.turkcell.commonpackage.utils.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedCustomerEvent implements Event {

    private String messages;
    private String status;

    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDateTime birthDate;
    private GenderType genderType;
    private String fatherName;
    private String motherName;

    private String nationalityNumber;

}
