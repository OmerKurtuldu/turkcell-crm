package com.turkcell.catalogService.entities.concretes;

import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "attributes")
public class Attribute extends BaseEntity<Integer> {

    @Column(name = "attribute_name")
    private String attributeName;

    @ManyToMany(mappedBy = "attributes")
    private List<Product> products;

    @OneToMany(mappedBy = "attribute")
    private List<ProductAttributeDetails> productAttributeDetails;
}
