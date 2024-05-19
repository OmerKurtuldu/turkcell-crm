package com.turkcell.categoryService.dataAccess.abstracts;

import com.turkcell.categoryService.entities.concretes.ProductAttributeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeDetailsRepository extends JpaRepository<ProductAttributeDetails, Integer> {
}
