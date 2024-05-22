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
@Table(name = "product_attribute_details")
public class ProductAttributeDetails extends BaseEntity<Integer> {


    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Column(name = "attribute_value", nullable = false)
    private String attributeValue;

    @ManyToMany(mappedBy = "productAttributeDetails")
    private List<Product> products; // Product ile ManyToMany ilişkisi
}
