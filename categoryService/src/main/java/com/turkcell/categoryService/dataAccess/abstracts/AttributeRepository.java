package com.turkcell.categoryService.dataAccess.abstracts;

import com.turkcell.categoryService.entities.concretes.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
