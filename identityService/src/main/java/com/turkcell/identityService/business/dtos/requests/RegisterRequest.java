package com.turkcell.identityService.business.dtos.requests;
import com.turkcell.identityService.entities.concretes.Role;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
