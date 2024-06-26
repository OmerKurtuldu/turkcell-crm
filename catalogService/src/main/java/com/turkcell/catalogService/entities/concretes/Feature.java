package com.turkcell.catalogService.entities.concretes;

import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "features")
public class Feature extends BaseEntity<Integer> {
    private String name;
}
