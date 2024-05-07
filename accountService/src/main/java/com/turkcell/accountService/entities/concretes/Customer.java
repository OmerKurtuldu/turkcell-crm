package com.turkcell.accountService.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name="id")
    private int id;
    @Column(name="firstName")
    private String firstName;

    @Column(name="secondName")
    private String secondName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="birthDate")
    private LocalDateTime birthDate;

    @Column(name="gender")
    private String gender;

    @Column(name="fatherName")
    private String fatherName;

    @Column(name="motherName")
    private String motherName;

    @Column(name="nationalityNumber")
    private String nationalityNumber;

}
