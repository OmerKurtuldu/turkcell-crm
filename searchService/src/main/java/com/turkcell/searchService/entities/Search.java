package com.turkcell.searchService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Search {

    @Id
    private String id;

    @Field(name = "customerId")
    private String customerId;

    @Field(name = "mobilePhone")
    private String mobilePhone;

    @Field(name = "firstName")
    private String firstName;

    @Field(name = "lastName")
    private String lastName;

}
