package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.ProductAttributeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeDetailsRepository extends JpaRepository<ProductAttributeDetails, Integer> {
}
