package com.turkcell.searchService.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    @Field(name = "firstName")
    private String firstName;

    @Field(name = "lastName")
    private String lastName;

    @Field(name = "nationalityNumber")
    private String nationalityNumber;

    @Field(name = "mobilePhone")
    private String mobilePhone;

}
