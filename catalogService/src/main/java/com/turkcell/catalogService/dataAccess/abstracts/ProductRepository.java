package com.turkcell.catalogService.dataAccess.abstracts;

import com.turkcell.catalogService.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
